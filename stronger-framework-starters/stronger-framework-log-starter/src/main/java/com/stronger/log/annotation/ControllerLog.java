package com.stronger.log.annotation;


import com.stronger.log.BusinessType;

import java.lang.annotation.*;

/**
 * @author stronger
 * @version release-1.0.0
 * @class ControllerLog.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc 自定义日志注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

    /**
     * 操作标题
     *
     * @return {@link String }
     */
    String title() default "";

    /**
     * 业务操作类型
     *
     * @return {@link BusinessType }
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否打印日志
     *
     * @return boolean
     */
    boolean isPrintLog() default true;

    /**
     * 是否保存日志到数据库
     *
     * @return boolean
     */
    boolean isSaveLog() default false;

    /**
     * 是否保存异常日志到数据库
     *
     * @return boolean
     */
    boolean isSaveExceptionLog() default false;
}
