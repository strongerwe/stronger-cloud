package com.stronger.platform.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginLockedDTO.class
 * @department Platform R&D
 * @date 2025/9/17
 * @desc 登录锁定DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLockedDTO implements Serializable {
    /**
     * 用户uuid
     */
    private String uuid;

    private String errCode;

    private String errMsg;

    /**
     * 锁定状态
     */
    private Boolean locked;

    public LoginLockedDTO(String uuid) {
        this.uuid = uuid;
        this.locked = Boolean.FALSE;
    }
}
