package com.stronger.platform.client.constants;


import com.stronger.commons.base.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum FrameCmdBizAdapters.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@AllArgsConstructor
@Getter
public enum FrameCmdBizAdapters implements BaseEnum {
    LOGIN("login", "登录"),
    ;

    private final String type;
    private final String value;
}
