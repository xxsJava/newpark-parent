package com.newpark.base.exception;


import com.newpark.base.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 *
 * @author : jack
 * @since 2023/3/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code;

    public BizException(ResponseCodeEnum resp) {
        super(resp.getMsg());
        this.msg = resp.getMsg();
        this.code = resp.getCode();
    }

    public BizException(String msg) {
        super(msg);
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.msg = msg;
    }

    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.code = ResponseCodeEnum.FAILURE.getCode();
        this.msg = msg;
    }

    public BizException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
