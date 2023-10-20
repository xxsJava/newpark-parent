package com.newpark.base.model.vo;


import cn.hutool.core.text.UnicodeUtil;
import com.newpark.base.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回响应格式
 *
 * @author jack
 * @date 2023/3/14
 */
@Accessors(chain = true)
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    private T data;

    public void setMsg(String msg) {
        this.msg = UnicodeUtil.toUnicode(msg);
    }

    public static <T> R<T> ok() {
        return restResult(null, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, ResponseCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ResponseCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMsg());
    }

    public static <T> R<T> failed(int code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(null, ResponseCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(ResponseCodeEnum codeEnum) {
        return restResult(null,codeEnum.getCode(), codeEnum.getMsg());
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
