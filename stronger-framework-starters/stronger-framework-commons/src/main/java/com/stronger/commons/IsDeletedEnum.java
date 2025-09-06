package com.stronger.commons;

import com.stronger.commons.base.BaseEnum;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum IsDeletedEnum.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc 是否删除枚举
 */
@Getter
public enum IsDeletedEnum implements BaseEnum {

    /**
     * 未删除
     */
    NORMAL("0", 0, Boolean.FALSE, "未删除"),
    /**
     * 已删除
     */
    DELETED("1", 1, Boolean.TRUE, "已删除");

    private final String type;
    private final Integer intVal;
    private final Boolean boolVal;
    private final String value;

    IsDeletedEnum(String type, Integer intVal, Boolean boolVal, String value) {
        this.type = type;
        this.value = value;
        this.boolVal = boolVal;
        this.intVal = intVal;
    }

}
