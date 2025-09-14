package com.stronger.log.aspect;

import com.google.gson.Gson;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.utils.StringUtils;
import com.stronger.commons.utils.UuidUtils;
import com.stronger.log.ControllerLogProperties;
import com.stronger.log.annotation.ControllerLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author stronger
 * @version release-1.0.0
 * @class ControllerLogAspect.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc controller日志打印AOP
 */
@Slf4j
@Aspect
@Order(-100)
@Configuration("ControllerLogAspect")
@EnableConfigurationProperties({ControllerLogProperties.class})
@ConditionalOnProperty(
        prefix = "stronger.framework.log",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class ControllerLogAspect {

    private final ControllerLogProperties properties;

    public ControllerLogAspect(ControllerLogProperties properties) {
        this.properties = properties;
        log.info(">>>>>>>> 配置启用自定义方法日志打印注解 @ControllerLog!");
    }

    @Around("@annotation(controllerLog)")
    public Object around(ProceedingJoinPoint joinPoint, ControllerLog controllerLog) throws Throwable {
        long beginTime = System.currentTimeMillis();
        String traceId = newTraceId();
        StringBuilder paramToString = new StringBuilder();
        try {
            StringBuilder methodMsg = this.getMethodMsg(joinPoint, controllerLog);
            if (!StringUtils.isNotNullOrNotEmpty(methodMsg)) {
                return joinPoint.proceed();
            } else {
                Object[] args = joinPoint.getArgs();
                if (args != null) {
                    for (Object p : args) {
                        try {
                            String paramJson = "";
                            if (p instanceof HttpServletRequest request) {
                                paramJson = request.getParameterMap() != null && !request.getParameterMap().isEmpty() ? (new Gson()).toJson(request.getParameterMap()) : "";
                            } else if (!(p instanceof HttpServletResponse)) {
                                paramJson = (new Gson()).toJson(p);
                            }

                            paramToString.append(paramJson);
                            paramToString.append("  ");
                        } catch (Exception var14) {
                            paramToString.append(p);
                            paramToString.append("  ");
                        }
                    }
                }
                this.log(controllerLog, "{}[{}]请求入参:{}", methodMsg.toString(), traceId, this.subMaxMessage(paramToString));
                Object result = joinPoint.proceed();
                long endTime = System.currentTimeMillis();
                long time = endTime - beginTime;
                this.log(controllerLog, "{}[{}]响应结果[{}ms]:{}", methodMsg.toString(), traceId, time, this.subMaxMessage((new Gson()).toJson(result)));
                // 保存日志到DB
                saveLog(controllerLog, methodMsg.toString(), paramToString, result);
                return result;
            }
        } catch (BizRuntimeException e) {
            StringBuilder methodMsg = this.getMethodMsg(joinPoint, controllerLog);
            long time = System.currentTimeMillis() - beginTime;
            log.info("{}[{}]自定义业务异常[{}ms]:{}", new Object[]{methodMsg, traceId, time, this.subMaxMessage((new Gson()).toJson(e.result()))});
            this.saveExceptionLog(controllerLog, methodMsg.toString(), paramToString, e);
            throw e;
        } catch (Throwable e) {
            StringBuilder methodMsg = this.getMethodMsg(joinPoint, controllerLog);
            long time = System.currentTimeMillis() - beginTime;
            log.error("{}[{}]出现异常[{}ms]:{} ", new Object[]{methodMsg, traceId, time, e.getMessage()});
            this.saveExceptionLog(controllerLog, methodMsg.toString(), paramToString, e);
            throw e;
        }
    }

    private String newTraceId() {
        return UuidUtils.fastUuid().substring(16).replaceAll("-", "");
    }

    private void saveLog(ControllerLog controllerLog, String methodMsg, StringBuilder paramToString, Object result) {
        if (controllerLog.isSaveLog()) {
            // TODO 实现日志保存到数据库 | 异步线程保存，成功失败均不可影响主业务
            log.info("{}|保存日志to数据库|{}", methodMsg, controllerLog.title());
        }
    }

    private void saveExceptionLog(ControllerLog controllerLog, String methodMsg, StringBuilder paramToString, Throwable e) {
        if (controllerLog.isSaveExceptionLog()) {
            // TODO 实现日志保存到数据库 | 异步线程保存，成功失败均不可影响主业务
            log.info("{}|保存异常日志to数据库|{}", methodMsg, controllerLog.title());
        }
    }

    private StringBuilder getMethodMsg(JoinPoint joinPoint, ControllerLog controllerLog) {
        Signature signature = joinPoint.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        StringBuilder methodMsg = null;
        methodMsg = new StringBuilder();
        methodMsg.append(this.subClassName(method.getDeclaringClass().getName()));
        methodMsg.append(".");
        methodMsg.append(method.getName());
        methodMsg.append("|");
        methodMsg.append(controllerLog.title());
        return methodMsg;
    }

    private void log(ControllerLog controllerLog, String var1, Object... var2) {
        if (controllerLog.isPrintLog()) {
            if ("INFO".equalsIgnoreCase(this.properties.getLevel())) {
                log.info(var1, var2);
            } else {
                if ("DEBUG".equalsIgnoreCase(this.properties.getLevel())) {
                    log.info(var1, var2);
                }

            }
        }
    }

    private String subClassName(String classPath) {
        return StringUtils.isEmpty(classPath) ? "" : classPath.substring(classPath.lastIndexOf(".") + 1);
    }

    private String subMaxMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return "";
        } else {
            return message.length() < this.properties.getMaxLength() ? message : message.substring(0, this.properties.getMaxLength());
        }
    }

    private String subMaxMessage(StringBuilder paramToString) {
        if (StringUtils.isEmpty(paramToString)) {
            return "";
        } else {
            String message = paramToString.toString();
            return message.length() < this.properties.getMaxLength() ? message : message.substring(0, this.properties.getMaxLength());
        }
    }
}
