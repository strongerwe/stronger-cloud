package com.stronger.platform.client.admin.frame;


import com.stronger.commons.RestResult;
import com.stronger.commons.base.BaseRequest;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.request.LoginVerificationCodeRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import com.stronger.platform.client.admin.frame.response.LoginUserInfoResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author stronger
 * @version release-1.0.0
 * @interface IFrameController.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 框架登录Admin接口
 */
public interface IFrameLoginAdminController {

    /**
     * 发送验证码
     *
     * @param httpServletRequest httpServletRequest
     * @param request            request
     * @return {@link RestResult }<{@link Void }>
     */
    @PostMapping("/platform/frame/admin/send-verification-code.open")
    RestResult<Void> sendVerificationCode(HttpServletRequest httpServletRequest,
                                          @RequestBody @Validated LoginVerificationCodeRequest request);

    /**
     * 登录
     *
     * @param httpServletRequest httpServletRequest
     * @param request            request
     * @return {@link RestResult }<{@link LoginResponse }>
     */
    @PostMapping("/platform/frame/admin/login.open")
    RestResult<LoginResponse> login(HttpServletRequest httpServletRequest,
                                    @RequestBody @Validated LoginRequest request);

    /**
     * 获取登录用户信息
     *
     * @param request request
     * @return {@link RestResult }<{@link LoginUserInfoResponse }>
     */
    @GetMapping("/platform/frame/admin/login-user-info")
    RestResult<LoginUserInfoResponse> loginUserInfo(BaseRequest request);
}
