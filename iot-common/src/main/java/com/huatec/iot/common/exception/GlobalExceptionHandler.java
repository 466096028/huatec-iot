package com.huatec.iot.common.exception;

import com.huatec.iot.common.response.Response;
import com.huatec.iot.common.enums.ResultCodeEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Iterator;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Response handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestUrl, e.getMessage());
        return Response.buildFailure(ResultCodeEnums.FAIL.code, "没有权限，请联系管理员授权");
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                        HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestUrl, e.getMethod());
        return Response.buildFailure(ResultCodeEnums.FAIL.code, e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Response handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error("【异常捕获】业务异常捕获:{}", e);
        Integer code = ResultCodeEnums.FAIL.code;
        if (e.getCode() !=null && e.getCode()>0) {
            code = e.getCode();
        }
        return Response.buildFailure(code, e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestUrl, e);
        return Response.buildFailure(ResultCodeEnums.FAIL.code, e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestUrl, e);
        return Response.buildFailure(ResultCodeEnums.FAIL.code, e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Response.buildFailure(ResultCodeEnums.PARAMS_VALID_FAIL.code, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("【异常捕获】自定义验证异常捕获:{}", e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        return Response.buildFailure(ResultCodeEnums.PARAMS_VALID_FAIL.code, message);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
        String message = "";
        while (iterator.hasNext()) {
            ConstraintViolation<?> next = iterator.next();
            String[] split = next.getPropertyPath().toString().split("\\.");
            message = split[split.length - 1] + next.getMessage();
            break;
        }
        return Response.buildFailure(ResultCodeEnums.FAIL.code, message);
    }

}
