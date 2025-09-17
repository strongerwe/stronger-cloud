package com.stronger.platform.client.constants;


import com.stronger.commons.base.IBaseResultCode;
import lombok.AllArgsConstructor;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum PlatformApiResultCode.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc API失败响应码
 */
@AllArgsConstructor
public enum PlatformApiResultCode implements IBaseResultCode {
    ;

    private final String code;
    private final String message;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
