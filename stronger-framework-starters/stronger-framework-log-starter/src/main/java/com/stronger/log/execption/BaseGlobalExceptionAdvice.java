package com.stronger.log.execption;

import com.stronger.commons.RestResult;
import com.stronger.commons.exception.BizRuntimeException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @author stronger
 * @version release-1.0.0
 * @class BaseGlobalExceptionAdvice.class
 * @department Platform R&D
 * @date 2025/9/8
 * @desc 公共异常处理
 */
public abstract class BaseGlobalExceptionAdvice {


    @ExceptionHandler({BizRuntimeException.class})
    @Order(Integer.MIN_VALUE)
    public RestResult<?> handlerWowjoyException(BizRuntimeException e) {
        return RestResult.exception(e);
    }


    @ExceptionHandler({ValidationException.class})
    public RestResult<?> handlerValidationException(ValidationException ex) {
        String[] messages = new String[2];
        if (ex instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException)ex;
            for(ConstraintViolation<?> c : exs.getConstraintViolations()) {
                String message = c.getMessage();
                if (message.length() == message.getBytes().length) {
                    messages[0] = message;
                } else {
                    messages[1] = message;
                }
            }
        }

        return new RestResult<>(messages[0], messages[1]);
    }

    @ExceptionHandler({BindException.class})
    public RestResult<?> handlerBindException(BindException ex) {
        String[] messages = new String[2];
        List<ObjectError> errorList = ex.getAllErrors();
        return this.getRestResult(messages, errorList);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResult<?> handlerMethodException(MethodArgumentNotValidException ex) {
        String[] messages = new String[2];
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        return this.getRestResult(messages, errorList);
    }

    @ExceptionHandler({Exception.class})
    public RestResult<?> handleException(Exception ex) {
        return new RestResult<>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
    }

    private RestResult<?> getRestResult(String[] messages, List<ObjectError> errorList) {
        for(ObjectError c : errorList) {
            String message = c.getDefaultMessage();
            if (message != null) {
                if (message.length() == message.getBytes().length) {
                    messages[0] = message;
                } else {
                    messages[1] = message;
                }

                return new RestResult<>(messages[0], messages[1]);
            }
        }

        return RestResult.exception();
    }
}
