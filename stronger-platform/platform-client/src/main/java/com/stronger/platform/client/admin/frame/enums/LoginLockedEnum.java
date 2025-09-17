package com.stronger.platform.client.admin.frame.enums;


import com.stronger.commons.base.BaseEnum;
import com.stronger.platform.client.constants.PlatformAdminResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum LoginLockedEnum.class
 * @department Platform R&D
 * @date 2025/9/17
 * @desc do what?
 */
@Getter
@AllArgsConstructor
public enum LoginLockedEnum implements BaseEnum {

    LOGIN_ERR_3("密码错误3次以上",
            "", PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_5M,
            PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR, 4, 300L),
    LOGIN_ERR_6("密码错误6次以上",
            "", PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_10M,
            PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR, 7, 600L),
    LOGIN_ERR_7("密码错误8次",
            "", PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_5M,
            PlatformAdminResultCode.SSO_LOGIN_ERR_COUNT2, 8, 600L),
    LOGIN_ERR_8("密码错误9次",
            "", PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_5M,
            PlatformAdminResultCode.SSO_LOGIN_ERR_COUNT1, 9, 600L),
    LOGIN_ERR_9("密码错误9次以上",
            "", PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_24H,
            PlatformAdminResultCode.SSO_LOGIN_LOCKED_ERR_24H, 10, 86400L);

    private final String type;
    private final String value;
    private final PlatformAdminResultCode lockedErrMsg;
    private final PlatformAdminResultCode afterErrMsg;
    private final Integer errCount;
    private final Long timeOut;

    public static PlatformAdminResultCode errMsg(Integer count) {
        if (count >= LOGIN_ERR_9.getErrCount()) {
            return LOGIN_ERR_9.getAfterErrMsg();
        } else if (count >= LOGIN_ERR_8.getErrCount()) {
            return LOGIN_ERR_8.getAfterErrMsg();
        } else if (count >= LOGIN_ERR_7.getErrCount()) {
            return LOGIN_ERR_7.getAfterErrMsg();
        } else {
            return count >= LOGIN_ERR_6.getErrCount() ? LOGIN_ERR_6.getAfterErrMsg() : LOGIN_ERR_3.getAfterErrMsg();
        }
    }

}
