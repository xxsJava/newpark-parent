package com.newpark.sso.filter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.newpark.redis.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author xxs18
 * @Description jwt sso 验证过滤
 * @Date 2023/10/9 16:31
 **/
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Resource
    private RedisUtils redis;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("登录认证");
        String token = request.getHeader("Content-TOKEN");
        log.info("获取到登录请求token,{}", token);
        /**
         * 1.token 存在登录过 不存在过期或没登录过
         **/
        String tokenKey = redis.get(token);

        // token 不存在 避免穿透
        if (tokenKey == null || tokenKey.isEmpty()) {
            redis.set("token", token, 1000 * 60);
        }
        return true;
    }
}
