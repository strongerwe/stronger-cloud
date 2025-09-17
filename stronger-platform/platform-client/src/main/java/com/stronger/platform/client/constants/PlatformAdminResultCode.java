package com.stronger.platform.client.constants;


import com.stronger.commons.base.IBaseResultCode;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum PlatformAdminResultCode.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 平台管理端失败响应码
 */
public enum PlatformAdminResultCode implements IBaseResultCode {
    USER_NOT_EXIST("10001", "用户不存在"),
    USER_ACCOUNT_PASSWORD("10002", "账号或密码输入错误，请重新输入！"),
    LOGIN_SMS_CODE_ERROR("10003", "登录验证码错误，请重新输入！"),
    USER_ACC_IS_DISABLE("10004", "您的账号当前不可用！"),
    // 账号锁定错误提示
    SSO_LOGIN_LOCKED_ERR_5M("10005", "您的操作过于频繁，请5分钟之后重试！"),
    SSO_LOGIN_LOCKED_ERR_10M("10006", "您的操作过于频繁，请10分钟之后重试！"),
    SSO_LOGIN_LOCKED_ERR("10007", "您的操作过于频繁，请稍后重试！"),
    SSO_LOGIN_LOCKED_ERR_24H("10008", "账号已锁定，24小时后自动解锁！"),
    SSO_LOGIN_ERR_COUNT1("10009","账号或密码输入错误，再错误1次将锁定！"),
    SSO_LOGIN_ERR_COUNT2("10010","账号或密码输入错误，再错误2次将锁定！"),
    ;

    private final String code;
    private final String message;

    PlatformAdminResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
