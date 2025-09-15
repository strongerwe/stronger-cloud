package com.stronger.encrypt.pwd;

import com.stronger.commons.base.BaseEnum;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtServiceAutoConfiguration.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 加密方式
 */
public enum EncryptType implements BaseEnum {
    CLIENT_SECRET("client_secret", "客户端秘钥"),
    USER_PASSWORD("user_password", "用户密码");

    private final String type;
    private final String value;

    EncryptType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}
