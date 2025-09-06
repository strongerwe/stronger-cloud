package com.stronger.commons;


import com.stronger.commons.base.BaseResponseTypeEnum;
import com.stronger.commons.base.BaseResultCode;
import com.stronger.commons.base.IBaseResultCode;
import com.stronger.commons.exception.BizRuntimeException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author stronger
 * @version release-1.0.0
 * @class RestResult.class
 * @department Platform R&D
 * @date 2025/9/6
 * @desc 响应响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RestResult<DATA> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private boolean succeed;
    /**
     * 响应代号
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应类型：'success' | 'info' | 'warning' | 'error'
     */
    private String responseType;
    /**
     * 响应数据
     */
    private DATA data;

    /**
     * 成功响应
     *
     * @return {@link RestResult }<{@link Void }>
     */
    public static RestResult<Void> voidSuccess() {
        return new RestResult<Void>(BaseResultCode.SUCCESS).setSucceed(Boolean.TRUE).setResponseType(BaseResponseTypeEnum.SUCCESS.getType());
    }

    /**
     * 成功响应
     *
     * @param data data
     * @return {@link RestResult }<{@link T }>
     */
    public static <T> RestResult<T> ok(T data) {
        return new RestResult<T>(Boolean.TRUE, BaseResultCode.SUCCESS.code(), BaseResultCode.SUCCESS.message(), BaseResponseTypeEnum.SUCCESS.getType(), data);
    }

    /**
     * 成功响应
     *
     * @param data data
     * @return {@link RestResult }<{@link DATA }>
     */
    public static <DATA> RestResult<DATA> success(DATA data) {
        RestResult<DATA> r = new RestResult<>(BaseResultCode.SUCCESS);
        r.setSucceed(Boolean.TRUE).setData(data).setResponseType(BaseResponseTypeEnum.SUCCESS.getType());
        return r;
    }

    /**
     * 失败
     *
     * @param reason 原因
     * @param target 目标
     * @return {@link RestResult}<{@link Data}>
     */
    public static <Data> RestResult<Data> failure(IBaseResultCode reason, Data target) {
        RestResult<Data> r = new RestResult<>(reason);
        return r.setSucceed(Boolean.FALSE).setData(target).setResponseType(BaseResponseTypeEnum.ERROR.getType());
    }

    /**
     * 失败返回
     *
     * @param code IBaseResultCode
     * @return {@link RestResult}<{@link Void}>
     */
    public static RestResult<Void> voidFail(IBaseResultCode code) {
        return new RestResult<>(code, BaseResponseTypeEnum.ERROR);
    }

    /**
     * @param code    代码
     * @param message 消息
     * @return {@link RestResult}<{@link Void}>
     */
    public static RestResult<Void> fatalFailure(String code, String message) {
        return new RestResult<Void>().setMessage(message).setCode(code).setSucceed(Boolean.FALSE).setResponseType(BaseResponseTypeEnum.ERROR.getType());
    }

    /**
     * 失败
     *
     * @param code         代码
     * @param message      消息
     * @param data         数据
     * @param responseType responseType
     * @return {@link RestResult}<{@link T}>
     */
    public static <T> RestResult<T> failure(String code, String message, T data, String responseType) {
        return new RestResult<T>(Boolean.FALSE, code, message, BaseResponseTypeEnum.ERROR.getType(), data).setResponseType(responseType);
    }

    /**
     * 异常响应
     *
     * @return {@link RestResult }<{@link ? }>
     */
    public static RestResult<?> exception() {
        return new RestResult<>(BaseResultCode.EXCEPTION, BaseResponseTypeEnum.ERROR);
    }

    /**
     * 异常响应
     *
     * @param e 异常
     * @return {@link RestResult }<{@link ? }>
     */
    public static RestResult<?> exception(BizRuntimeException e) {
        return new RestResult<>(Boolean.FALSE, e.getCode(), e.getMessage(), BaseResponseTypeEnum.ERROR);
    }

    /*================ 构造方法 ==================*/
    public RestResult(IBaseResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.responseType = resultCode.code().equals(BaseResultCode.SUCCESS.code()) ? BaseResponseTypeEnum.SUCCESS.getType() : BaseResponseTypeEnum.ERROR.getType();
    }

    public RestResult(IBaseResultCode resultCode, BaseResponseTypeEnum responseTypeEnum) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.responseType = responseTypeEnum.getType();
    }

    public RestResult(String code, String message) {
        this.code = code;
        this.message = message;
        this.responseType = BaseResultCode.SUCCESS.code().equals(code) ?
                BaseResponseTypeEnum.SUCCESS.getType() : BaseResponseTypeEnum.ERROR.getType();
    }

    public RestResult(Boolean succeed, String code, String message, DATA data) {
        this.code = code;
        this.message = message;
        this.responseType = BaseResultCode.SUCCESS.code().equals(code) ?
                BaseResponseTypeEnum.SUCCESS.getType() : BaseResponseTypeEnum.ERROR.getType();
        this.data = data;
    }

    public RestResult(Boolean succeed, String code, String message, BaseResponseTypeEnum responseTypeEnum) {
        this.succeed = succeed;
        this.code = code;
        this.message = message;
        this.responseType = responseTypeEnum.getType();
    }

    /*================ 验证方法 ==================*/
    public static boolean validate(RestResult<?> result) {
        return validateCode(result) && Objects.nonNull(result.getData());
    }

    public static boolean validateCode(RestResult<?> result) {
        return BaseResultCode.SUCCESS.code().equals(result.getCode());
    }
}
