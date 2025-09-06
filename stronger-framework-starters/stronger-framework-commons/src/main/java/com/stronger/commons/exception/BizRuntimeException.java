package com.stronger.commons.exception;


import com.stronger.commons.base.BaseResultCode;
import com.stronger.commons.base.IBaseResultCode;
import com.stronger.commons.RestResult;
import lombok.Getter;

import java.io.Serial;

/**
 * @author stronger
 * @version release-1.0.0
 * @class BizRuntimeException.class
 * @department Platform R&D
 * @date 2025/9/6
 * @desc 自定义运行异常
 */
@Getter
public class BizRuntimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -112334205699475132L;

    /**
     * 响应状态码
     */
    private final String code;

    public BizRuntimeException(Throwable e) {
        super(e.getMessage(), e);
        this.code = BaseResultCode.EXCEPTION.code();
    }


    public BizRuntimeException(IBaseResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
    }

    public BizRuntimeException(String message) {
        super(message);
        this.code = BaseResultCode.EXCEPTION.code();
    }

    public BizRuntimeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = BaseResultCode.EXCEPTION.code();
    }

    public RestResult<?> result() {
        return RestResult.exception(this);
    }
}