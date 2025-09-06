package com.stronger.commons.base;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum BaseResultCode.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc 响应类型（当前支持：success,info,warning,error）
 */
public enum BaseResponseTypeEnum implements BaseEnum {
    SUCCESS("success"),
    INFO("info"),
    WARNING("warning"),
    ERROR("error");

    private final String type;

    BaseResponseTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return type;
    }
}
