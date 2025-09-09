package com.stronger.redis.redisson;

import cn.hutool.core.util.StrUtil;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.utils.NewBeeParserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author stronger
 * @version release-1.0.0
 * @class FastJsonRedisSerializer.class
 * @department Platform R&D
 * @date 2025/9/9
 * @desc do what?
 */
@Slf4j
@Aspect
@Order(-181)
@Configuration("ControllerLogAutoConfigurer")
@EnableConfigurationProperties({RedissonLockProperties.class})
@ConditionalOnProperty(
        prefix = "stronger.framework.redisson",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class RedissonLockAspect {

    private final RedissonClient redissonClient;

    public RedissonLockAspect(RedissonClient redissonClient) {
        log.info(">>>>>>>> @RedissonLock [分布式锁注解] Running Success!");
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(redissonLock)")
    public Object cacheDataAround(ProceedingJoinPoint pjp, RedissonLock redissonLock) throws Throwable {
        String lockKey = NewBeeParserUtils.parse(redissonLock.keyTemplate(), parse(getMethod(pjp), pjp.getArgs(), redissonLock.suffix()));
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean tryLock = lock.tryLock(redissonLock.lockTime(), redissonLock.unit());
            if (tryLock) {
                return pjp.proceed();
            }
        } catch (InterruptedException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error("[InterruptedException]tryLock系统异常! 请检查调用和系统日志", e);
            throw new BizRuntimeException("[InterruptedException]tryLock系统异常，请稍后重试！");
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        throw new BizRuntimeException(com.stronger.commons.base.BaseResultCode.TRANS_LOCK_DOWN);
    }

    private final ExpressionParser parser = new SpelExpressionParser();
    private final ParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();

    private Object parse(Method method, Object[] arguments, String spl) {
        try {
            String[] params = discoverer.getParameterNames(method);
            // 处理参数名无法获取的情况
            if (params == null) {
                log.warn("无法获取方法[{}]的参数名，将使用默认参数名", method.getName());
                params = new String[arguments.length];
                for (int i = 0; i < arguments.length; i++) {
                    params[i] = "p" + i;
                }
            }
            EvaluationContext context = new StandardEvaluationContext();
            for (int len = 0; len < params.length; len++) {
                context.setVariable(params[len], arguments[len]);
            }
            try {
                Expression expression = parser.parseExpression(spl);
                return expression.getValue(context);
            } catch (Exception e) {
                return StrUtil.EMPTY;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = pjp
                        .getTarget()
                        .getClass()
                        .getDeclaredMethod(pjp.getSignature().getName(),
                                method.getParameterTypes());
            } catch (SecurityException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return method;
    }

}
