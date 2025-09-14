package com.stronger.platform.app.admin.frame.cmd;


import com.alibaba.fastjson.JSONObject;
import com.stronger.commons.framework.AbstractCmdExe;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PhoneVerificationCodeCmdExe.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 手机号验证码登录
 */
@Slf4j
@Component("PhoneVerificationCodeLoginCmdExe")
public class PhoneVerificationCodeLoginCmdExe extends AbstractCmdExe<LoginRequest, LoginResponse> {

    @Override
    protected LoginResponse cmdExecute(LoginRequest request) {
        // TODO 执行手机号验证码登录

        return new LoginResponse("PhoneVerificationCodeLoginCmdExe", 2000L, "PhoneVerificationCodeLoginCmdExe");
    }

    @Override
    protected void validate(LoginRequest request) {
        // TODO 校验手机号验证码登录入参

        log.info("PhoneVerificationCodeLoginCmdExe.validate|校验手机号验证码登录|{}", JSONObject.toJSONString(request));
    }
}
