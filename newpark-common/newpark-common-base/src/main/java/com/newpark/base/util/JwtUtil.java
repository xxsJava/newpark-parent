package com.newpark.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.SneakyThrows;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author xxs18
 * @Description jwt封装类
 * @Date 2023/10/9 14:35
 **/
public class JwtUtil {

    private static final String SIGN_CODE = "3af2e5afcb654f0534c24b72fb2db5badfc22086c7ff6e4794ff95f7a30c5ac8bfbb310037f0e7b8fe4b78e9f79e129830169fa373b4a2ec047523cb5351e2c0";

    /**
     * 生成token
     * @param map 传入payload
     * @return 返回token
     */
    @SneakyThrows
    public static <T> String getToken(Map<String,T> map) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            if(k != null && v!=null){
                builder.withClaim(k, v.toString());
            }
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60*24*7);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(SIGN_CODE)).toString();
    }

    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(@NotEmpty String token) throws UnsupportedEncodingException {
        return JWT.require(Algorithm.HMAC256(SIGN_CODE)).build().verify(token);
    }
}
