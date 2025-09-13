package com.stronger.commons.base;

/**
 * @author stronger
 * @version release-1.0.0
 * @enum BaseResultCode.class
 * @department Platform R&D
 * @date 2025/8/7
 * @desc 基础公共响应码
 */
public enum BaseResultCode implements IBaseResultCode {

    SUCCESS("0", "成功"),
    FAIL("1", "失败"),
    HTTP_401("HTTP-401", "当前访问未授权."),
    HTTP_405("HTTP-405", "请求方式{0}不被支持."),
    EXCEPTION("500", "系统异常，请联系管理员！"),
    FUSING_504("FUSING-504", "服务繁忙，请稍后重试！"),
    PARAM_IS_EMPTY("400", "请求参数[{0}]不能为空"),
    /**
     * 分页查询单页数量超过最大值
     */
    PAGE_SIZE_EXCEED("400", "分页查询Size超过设置最大值！"),
    TRANS_LOCK_DOWN("LOCK-403", "当前提交正在处理中, 请勿频繁操作!");

    private final String code;
    private final String message;
    BaseResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }
    @Override
    public String message() {
        return message;
    }
}
