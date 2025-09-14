package com.stronger.commons.framework;


import com.stronger.commons.Exe;
import com.stronger.commons.exception.BizRuntimeException;
import org.springframework.context.ApplicationContext;

/**
 * @author stronger
 * @version release-1.0.0
 * @class PlatformFrameworkCore.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc PlatformFrameworkCore
 */
public class PlatformFrameworkCore {

    private final ApplicationContext ioc;

    public PlatformFrameworkCore(ApplicationContext ioc) {
        this.ioc = ioc;
    }

    public <T extends CmdExeResponse> T execute(CmdExeRequest<T> request) {
        // 匹配适配器
        CmdExeAdapter<T> adapter = adapter(request.getCmdBizAdapter());
        // 适配器匹配EXE
        Exe<CmdExeRequest<T>, T> exe = adapter.adapterCmdExe(request.getCmdBizExe());
        // 执行EXE
        return exe.execute(request);
    }


    private <T extends CmdExeResponse> CmdExeAdapter<T> adapter(final String cmdBizAdapter) {
        String beanName = cmdBizAdapter + "CmdExeAdapter";
        if (ioc.containsBean(beanName)) {
            CmdExeAdapter<T> adapter = ioc.getBean(beanName, CmdExeAdapter.class);
            if (adapter == null) {
                throw new BizRuntimeException("[500](" + beanName + ")API接口初始化失败!");
            }
            return adapter;
        }
        throw new BizRuntimeException("[404](" + beanName + ")API接口不存在! 请检查接口配置");
    }
}
