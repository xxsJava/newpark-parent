package com.newpark.base.model.vo;

import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.common.enums.KeySm2;
import com.newpark.redis.utils.RedisUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.security.KeyPair;

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

    public static <T> R<T> ok(T data, boolean safe, String msg) {

//        if (safe) {
//            SM2 sm2 = SmUtil.sm2(KeySm2.PRIVATE_KEY.getKey(), KeySm2.PUBLIC_KEY.getKey());
//            return (R<T>)restResult(sm2.encryptBcd(data.toString(), KeyType.PublicKey),
//                ResponseCodeEnum.SUCCESS.getCode(), msg);
//        }

        return restResult(data, ResponseCodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed() {
        return restResult( ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return restResult(ResponseCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, ResponseCodeEnum.FAILURE.getCode(), ResponseCodeEnum.FAILURE.getMsg());
    }

    public static <T> R<T> failed(int code, String msg) {
        return restResult(code, msg);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, ResponseCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> R<T> failed(ResponseCodeEnum codeEnum) {
        return restResult(codeEnum.getCode(), codeEnum.getMsg());
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    private static <T> R<T> restResult(int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
