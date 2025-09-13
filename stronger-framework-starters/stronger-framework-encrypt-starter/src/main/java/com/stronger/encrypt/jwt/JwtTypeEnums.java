package com.stronger.encrypt.jwt;

import com.stronger.commons.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformJwtService.class
 * @department Platform R&D
 * @date 2025/9/12
 * @desc Jwt类型
 */
@Getter
@AllArgsConstructor
public enum JwtTypeEnums implements BaseEnum {
    /**
     * 客户端API传递的真实Jwt token
     */
    CLIENT_TOKEN("client_token", "客户端API"),
    /**
     * 登录token传递封装后的token（需要去redis换取真实token）
     */
    APP_TOKEN("app_token", "登录TOKEN");
    private final String type;
    private final String value;
}
