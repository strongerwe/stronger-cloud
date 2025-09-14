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
 * @class PasswordLoginCmdExe.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 账号密码登录
 */
@Slf4j
@Component(value = "PasswordLoginCmdExe")
public class PasswordLoginCmdExe extends AbstractCmdExe<LoginRequest, LoginResponse> {

    @Override
    protected LoginResponse cmdExecute(LoginRequest request) {

        LoginResponse passwordLoginCmdExe = new LoginResponse("PasswordLoginCmdExe", 2000L);
        passwordLoginCmdExe.setCmdExe("PasswordLoginCmdExe");
        return passwordLoginCmdExe;
    }

    @Override
    protected void validate(LoginRequest request) {
        // TODO 验证入参参数
        log.info("PasswordLoginCmdExe.validate|校验账号密码登录入参|{}", JSONObject.toJSONString(request));
    }
}
