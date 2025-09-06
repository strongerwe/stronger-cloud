package com.stronger.commons.base;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum BaseResultCode.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc 枚举类基础接口
 */
public interface BaseEnum {

    @JsonValue
    String getType();

    String getValue();
}
