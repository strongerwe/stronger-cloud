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
    USER_NOT_EXIST("10001", "用户不存在"),
    USER_ACCOUNT_PASSWORD("10002", "账号或密码输入错误，请重新输入！"),
    LOGIN_SMS_CODE_ERROR("10003", "登录验证码错误，请重新输入！"),
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
