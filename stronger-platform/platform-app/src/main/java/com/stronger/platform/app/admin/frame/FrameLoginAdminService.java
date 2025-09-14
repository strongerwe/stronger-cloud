package com.stronger.platform.app.admin.frame;


import com.stronger.commons.RestResult;
import com.stronger.commons.framework.PlatformFrameworkCore;
import com.stronger.platform.client.admin.frame.request.LoginRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
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

    public RestResult<LoginResponse> login(LoginRequest request) {
        LoginResponse response = platformFrameworkCore.execute(request);
        return RestResult.success(response);
    }
}
