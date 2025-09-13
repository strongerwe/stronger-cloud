package com.stronger.gateway.config;


import com.stronger.commons.exception.BaseGlobalExceptionAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author stronger
 * @version release-1.0.0
 * @class GlobalExceptionAdvice.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc 全局异常过滤器
 */
@RestControllerAdvice
public class GlobalExceptionAdvice extends BaseGlobalExceptionAdvice {
}
