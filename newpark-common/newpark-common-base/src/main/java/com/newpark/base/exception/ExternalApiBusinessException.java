package com.newpark.base.exception;


import com.newpark.base.enums.ResponseCodeEnum;

/**
 * 调用第三方异常
 *
 * @author jack
 * @since 2023/3/14
 */
public class ExternalApiBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;

    public ExternalApiBusinessException() {
        this.code = ResponseCodeEnum.EXTERNAL_API_ERROR.getCode();
        this.msg = ResponseCodeEnum.EXTERNAL_API_ERROR.getMsg();
    }

    public ExternalApiBusinessException(String message) {
        super(message);
        this.code = ResponseCodeEnum.EXTERNAL_API_ERROR.getCode();
        this.msg = message;
    }

    public ExternalApiBusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public ExternalApiBusinessException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum.getMsg());
        this.code = responseCodeEnum.getCode();
        this.msg = responseCodeEnum.getMsg();
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public ExternalApiBusinessException setCode(int code) {
        this.code = code;
        return this;
    }

    public ExternalApiBusinessException setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
