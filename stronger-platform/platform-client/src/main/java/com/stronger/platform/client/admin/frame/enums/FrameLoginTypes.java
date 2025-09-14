package com.stronger.platform.client.admin.frame.enums;


import com.stronger.commons.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum FrameLoginTypes.class
 * @department Platform R&D
 * @date 2025/9/14
 * @desc 框架登录类型
 */
@Getter
@AllArgsConstructor
public enum FrameLoginTypes implements BaseEnum {
    PASSWORD("Password", "密码登录"),
    PHONE_VERIFICATION_CODE("PhoneVerificationCode", "手机验证码登录"),
    HIS_TOKEN("HisToken", "His系统token登录"),
    ;

    private final String type;
    private final String value;
}
