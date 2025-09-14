package com.stronger.platform.adapter.admin.frame;


import com.stronger.commons.RestResult;
import com.stronger.log.annotation.ControllerLog;
import com.stronger.platform.app.admin.frame.FrameLoginAdminService;
import com.stronger.platform.client.admin.frame.IFrameLoginAdminController;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.request.LoginVerificationCodeRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stronger
 * @version release-1.0.0
 * @class FrameLoginAdminController.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 框架登录Admin接口
 */
@RestController
public class FrameLoginAdminController implements IFrameLoginAdminController {

    @Resource
    private FrameLoginAdminService frameLoginAdminService;

    @Override
    @ControllerLog(title = "发送验证码")
    public RestResult<Void> sendVerificationCode(HttpServletRequest httpServletRequest,
                                                 LoginVerificationCodeRequest request) {
        return null;
    }

    @Override
    @ControllerLog(title = "登录")
    public RestResult<LoginResponse> login(HttpServletRequest httpServletRequest,
                                           LoginRequest request) {
        return frameLoginAdminService.login(request);
    }
}
