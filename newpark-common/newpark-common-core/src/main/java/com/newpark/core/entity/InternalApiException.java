package com.newpark.core.entity;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * feign error 处理类
 *
 * @author jack
 * @date 2023/3/14
 */
public class InternalApiException extends HystrixBadRequestException {

    private String msg;
    private Integer code;

    public InternalApiException(String msg, Integer code) {
        super(msg);
        this.code = code;
        this.msg = msg;

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
