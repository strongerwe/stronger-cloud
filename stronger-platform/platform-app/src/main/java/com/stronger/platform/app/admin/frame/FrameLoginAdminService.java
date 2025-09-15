package com.stronger.platform.app.admin.frame;


import com.stronger.commons.AssertCheck;
import com.stronger.commons.RestResult;
import com.stronger.commons.base.BaseRequest;
import com.stronger.commons.framework.PlatformFrameworkCore;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import com.stronger.platform.client.admin.frame.response.LoginUserInfoResponse;
import com.stronger.platform.client.constants.PlatformApiResultCode;
import com.stronger.platform.domain.user.entity.SysUserInfo;
import com.stronger.platform.domain.user.gateway.SysUserInfoGateway;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author stronger
 * @version release-1.0.0
 * @class FrameLoginAdminService.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc do what?
 */
@Service
public class FrameLoginAdminService {

    @Resource
    private PlatformFrameworkCore platformFrameworkCore;

    @Resource
    private SysUserInfoGateway sysUserInfoGateway;

    public RestResult<LoginResponse> login(LoginRequest request) {
        LoginResponse response = platformFrameworkCore.execute(request);
        return RestResult.success(response);
    }

    public RestResult<LoginUserInfoResponse> loginUserInfo(BaseRequest request) {
        SysUserInfo userInfo = sysUserInfoGateway.getByUserId(request.getFrameUserId());
        AssertCheck.notNull(userInfo, PlatformApiResultCode.USER_NOT_EXIST);
        LoginUserInfoResponse response = new LoginUserInfoResponse(
                userInfo.getRealName(),
                userInfo.getSex(),
                userInfo.getIdCard(),
                userInfo.getMobile(),
                userInfo.getEmail());
        return RestResult.success(response);
    }
}
