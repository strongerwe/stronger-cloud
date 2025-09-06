package com.stronger.commons.base;


/**
 * @author stronger
 * @version release-1.0.0
 * @interface IBaseResultCode.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc 响应枚举接口
 */
public interface IBaseResultCode {

    /**
     * 响应code
     *
     * @return String
     */
    String code();

    /**
     * 响应message
     *
     * @return string
     */
    String message();
}
