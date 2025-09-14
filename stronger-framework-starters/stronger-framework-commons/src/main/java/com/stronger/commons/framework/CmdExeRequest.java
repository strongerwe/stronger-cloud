package com.stronger.commons.framework;


import com.stronger.commons.base.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author stronger
 * @version release-1.0.0
 * @class CmdExeRequest.class
 * @department Platform R&D
 * @date 2025/9/13
 * @desc CmdExeRequest
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public abstract class CmdExeRequest<T extends CmdExeResponse> extends BaseRequest {

    /**
     * cmd业务编码: 根据此此参数匹配Adapter
     */
    public abstract String getCmdBizAdapter();

    /**
     * 适配业务参数:根据哪个业务参数进行匹配CmdExe
     *
     * @return {@link String }
     */
    public abstract String getCmdBizExe();

    /**
     * 获取响应结果Clazz
     *
     * @return clazz
     */
    public abstract Class<T> getFrameResponseClass();

}
