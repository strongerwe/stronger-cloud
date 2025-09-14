package com.stronger.platform.app.admin.frame.adapter;


import com.stronger.commons.Exe;
import com.stronger.commons.exception.BizRuntimeException;
import com.stronger.commons.framework.CmdExeAdapter;
import com.stronger.commons.framework.CmdExeRequest;
import com.stronger.platform.client.admin.frame.response.LoginResponse;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author stronger
 * @version release-1.0.0
 * @class LoginCmdExeAdapter.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc 登录验证码
 */
@Component("loginCmdExeAdapter")
public class LoginCmdExeAdapter implements CmdExeAdapter<LoginResponse> {

    @Resource
    private ApplicationContext ioc;

    @Override
    public Exe<CmdExeRequest<LoginResponse>, LoginResponse> adapterCmdExe(String cmdBizExe) {
        String cmdExeBean = cmdBizExe + "LoginCmdExe";
        if (ioc.containsBean(cmdExeBean)) {
            Exe<CmdExeRequest<LoginResponse>, LoginResponse> exe = ioc.getBean(cmdExeBean, Exe.class);
            if (exe == null) {
                throw new BizRuntimeException("[500](" + cmdBizExe + ")登录接口适配失败，请联系管理员！");
            }
            return exe;
        }
        throw new BizRuntimeException("[404](" + cmdBizExe + ")登录接口不存在! 请联系管理员！");
    }
}
