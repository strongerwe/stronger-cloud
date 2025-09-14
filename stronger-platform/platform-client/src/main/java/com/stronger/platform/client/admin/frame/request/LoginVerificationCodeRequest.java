package com.stronger.platform.client.admin.frame.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginVerificationCodeRequest.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 登录验证码请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginVerificationCodeRequest implements Serializable {

    /**
     * 发送手机号
     */
    @NotNull(message = "接收手机号不能为空")
    private String phone;

    /**
     * 验证码类型
     */
    @NotNull(message = "验证码类型不能为空")
    private String msgType;

}
