package com.stronger.commons.base;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum BaseRequestAttributeEnums.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@AllArgsConstructor
@Getter
public enum BaseRequestAttributeEnums implements BaseEnum {
    FRAME_CLIENT_ID("frameClientId", "客户端ID", "Frame-Client-Id", "fci"),
    FRAME_USER_ID("frameUserId", "用户ID", "Frame-User-Id", "fui"),
    FRAME_USER_NAME("frameUserName", "用户名称", "Frame-User-Name", "fun"),
    FRAME_USER_VERSION("frameUserVersion", "用户版本", "Frame-User-Version", "fuv"),
    FRAME_ORG_CODE("frameOrgCode", "组织编码", "Frame-Org-Code", "foc"),
    ;
    private final String type;
    private final String value;
    private final String headerName;
    private final String jwtKey;
}
