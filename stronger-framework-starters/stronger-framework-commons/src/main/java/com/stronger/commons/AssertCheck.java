package com.stronger.commons;


import com.stronger.commons.base.IBaseResultCode;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.utils.StringUtils;

import java.util.Collection;

/**
 * @author stronger
 * @version release-1.0.0
 * @class AssertCheck.class
 * @department Platform R&D
 * @date 2025/9/6
 * @desc 断言抛异常工具类
 */
public class AssertCheck {

    /**
     * 验证expression是否为True
     *
     * @param expression expression
     * @param resultCode resultCode
     */
    public static void isTrue(boolean expression, IBaseResultCode resultCode) {
        if (!expression) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证expression是否为True
     *
     * @param expression expression
     * @param code       code
     * @param message    message
     */
    public static void isTrue(boolean expression, String code, String message) {
        if (!expression) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证expression是否非True
     *
     * @param expression expression
     * @param resultCode resultCode
     */
    public static void isFalse(boolean expression, IBaseResultCode resultCode) {
        if (expression) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证expression是否非True
     *
     * @param expression expression
     * @param code       code
     * @param message    message
     */
    public static void isFalse(boolean expression, String code, String message) {
        if (expression) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证object是否为Null
     *
     * @param object     object
     * @param resultCode resultCode
     */
    public static void isNull(Object object, IBaseResultCode resultCode) {
        if (object != null) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证object是否为Null
     *
     * @param object  object
     * @param code    code
     * @param message message
     */
    public static void isNull(Object object, String code, String message) {
        if (object != null) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证object是否非Null
     *
     * @param object     object
     * @param resultCode resultCode
     */
    public static void notNull(Object object, IBaseResultCode resultCode) {
        if (object == null) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证object是否非Null
     *
     * @param object  object
     * @param code    code
     * @param message message
     */
    public static void notNull(Object object, String code, String message) {
        if (object == null) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证str是否Empty
     *
     * @param str        str
     * @param resultCode resultCode
     */
    public static void isEmpty(String str, IBaseResultCode resultCode) {
        if (StringUtils.isNotEmpty(str)) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证str是否Empty
     *
     * @param str     str
     * @param code    code
     * @param message message
     */
    public static void isEmpty(String str, String code, String message) {
        if (StringUtils.isNotEmpty(str)) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证str是否Empty
     *
     * @param str        str
     * @param resultCode resultCode
     */
    public static void notEmpty(String str, IBaseResultCode resultCode) {
        if (str == null || str.trim().isEmpty()) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证str是否Empty
     *
     * @param str     str
     * @param code    code
     * @param message message
     */
    public static void notEmpty(String str, String code, String message) {
        if (str == null || str.trim().isEmpty()) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证coll不为空
     *
     * @param coll       coll
     * @param resultCode resultCode
     */
    public static void notEmpty(Collection<?> coll, IBaseResultCode resultCode) {
        if (coll == null || coll.isEmpty()) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证coll不为空
     *
     * @param coll    coll
     * @param code    code
     * @param message message
     */
    public static void notEmpty(Collection<?> coll, String code, String message) {
        if (coll == null || coll.isEmpty()) {
            throw new BizRuntimeException(code, message);
        }
    }

    /**
     * 验证集合为空
     *
     * @param coll       coll
     * @param resultCode resultCode
     */
    public static void isEmpty(Collection<?> coll, IBaseResultCode resultCode) {
        if (coll != null && !coll.isEmpty()) {
            throw new BizRuntimeException(resultCode);
        }
    }

    /**
     * 验证集合为空
     *
     * @param coll    coll
     * @param code    code
     * @param message message
     */
    public static void isEmpty(Collection<?> coll, String code, String message) {
        if (coll != null && !coll.isEmpty()) {
            throw new BizRuntimeException(code, message);
        }
    }

}
