package com.stronger.platform.client.admin.frame.request;


import com.stronger.commons.framework.CmdExeRequest;
import com.stronger.platform.client.admin.frame.enums.FrameLoginTypes;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import com.stronger.platform.client.constants.FrameCmdBizAdapters;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginCmdExeRequest.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends CmdExeRequest<LoginResponse> {

    /**
     * @see FrameLoginTypes
     */
    @NotEmpty(message = "登录方式不能为空")
    private String loginType;

    @NotNull(message = "登录信息对象不能为空")
    private LoginModel model;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class LoginModel implements Serializable {

        private String username;
        private String password;

        private String phone;
        private String verificationCode;

        private String authToken;
    }

    /**
     * cmd业务编码: 根据此此参数匹配Adapter
     *
     * @return {@link String }
     * @see FrameCmdBizAdapters
     */
    @Override
    public String getCmdBizAdapter() {
        return FrameCmdBizAdapters.LOGIN.getType();
    }

    @Override
    public String getCmdBizExe() {
        return this.loginType;
    }

    @Override
    public Class<LoginResponse> getFrameResponseClass() {
        return LoginResponse.class;
    }
}
