package com.stronger.commons.framework;


import com.stronger.commons.Exe;

/**
 * @author stronger
 * @version release-1.0.0
 * @interface CmdExeAdapter.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc CmdExe适配器
 */
public interface CmdExeAdapter<T extends CmdExeResponse> {

    /**
     * 适配CmdExe
     *
     * @param cmdBizExe 适配Exe业务参数
     * @return {@link Exe }<{@link CmdExeRequest }<{@link ? }>, {@link CmdExeResponse }>
     */
    Exe<CmdExeRequest<T>, T> adapterCmdExe(String cmdBizExe);

}
