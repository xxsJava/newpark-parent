package com.newpark.core.advice;


import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.exception.BizException;
import com.newpark.base.model.vo.R;
import com.newpark.core.entity.InternalApiException;
import com.newpark.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author jack
 * @since 2023/3/14
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public R handleBizException(BizException e) {
        return R.failed(e.getCode(), e.getMsg());
    }

    /**
     * 数据库唯一主键冲突
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        return R.failed(ResponseCodeEnum.DATABASE_DUPLICATEKEY.getCode(), ResponseCodeEnum.DATABASE_DUPLICATEKEY.getMsg());
    }

    /**
     * 实体验证
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validException(MethodArgumentNotValidException mnve) {
        return R.failed(mnve.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MultipartException.class)
    public R handleMultipartException(MultipartException ex, HttpServletRequest request) {
        // 处理文件上传过大异常
        log.error("文件上传异常:{}", ex.getMessage());
        return R.failed("文件上传失败，服务异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleException(HttpServletRequest request, Exception e) {
        log.error("url={}:{}{}", request.getServerName(), request.getServerPort(), request.getRequestURI());
        log.error("exception:{}", StringUtils.getExceptionInfo(e));
        //参数校验异常
        if (e instanceof BindException){
            BindException exception = (BindException) e ;
            String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("/"));
            return R.failed(message);
        }
        return R.failed("服务器异常，异常信息: " + e.getMessage());
    }

    /**
     * 缺少参数统一处理
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<?> handleException(final MissingServletRequestParameterException e) {
        log.warn("MissingParameter: {}", e.getMessage());
        return R.failed("MissingParameter:" + e.getMessage());
    }

    /**
     * feign error统一处理
     */
    @ExceptionHandler(InternalApiException.class)
    public R<?> handleException(InternalApiException e) {
        log.warn("feignApiException: {}", e.getMessage());
        return R.failed(e.getMessage());
    }

}
