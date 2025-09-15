package com.stronger.platform.client.admin.frame.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginUserInfoResponse.class
 * @department Platform R&D
 * @date 2025/9/15
 * @desc 登录用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginUserInfoResponse implements Serializable {

    /**
     * 用户名称
     */
    private String realName;

    /**
     * 性别(sys_sex:0.未知,1.女,2.男)
     */
    private Byte sex;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱地址
     */
    private String email;

}
