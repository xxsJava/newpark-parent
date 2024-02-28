package com.newpark.core.utils;

import com.newpark.base.constant.Constant;
import com.newpark.base.exception.BizException;
import com.newpark.base.util.JwtUtil;
import com.newpark.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author xxs18
 * @Description 登录校验
 * @Date 2023/12/20 9:49
 **/
@Slf4j
public class LoginVerify {

    public static LoginVerify create() {
        return new LoginVerify();
    }

    /**
     * 登录校验获取jwt 加密数据
     * @param param
     * @return
     */
    public String getUsrJWTData(String param,RedisUtils redisUtils) {
        //获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取 header 的token 值
        String token = request.getHeader(Constant.Token.REQUEST_HEADER);
        try {
            log.info("redis-token---->{}",redisUtils.get(token));
            return JwtUtil.getToken(redisUtils.get(token)).getClaim(param).asString();
        } catch (Exception e) {
            throw new BizException(10000,"登录校验失败");
        }
    }

    /**
     * 获取请求token
     * @return
     */
    public String getUsrToken(){
        //获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(Constant.Token.REQUEST_HEADER);
    }
}
