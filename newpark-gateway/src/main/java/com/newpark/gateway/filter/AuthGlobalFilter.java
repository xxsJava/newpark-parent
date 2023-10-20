package com.newpark.gateway.filter;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import cn.hutool.core.util.ReUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.newpark.base.util.JwtUtil;
import com.newpark.redis.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author xxs18
 * @Description 全局过滤器 认证过滤器
 * @Date 2023/8/22 16:29
 **/
@Slf4j
@Component
@Valid
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private RedisUtils redis;
    public static List<String> routs = new LinkedList<>();
    static {
        routs.add("/usr/.*");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("开始执行权限认证");
        // 获取请求
        ServerHttpRequest request = exchange.getRequest();
        log.info("访问的接口---{}", request.getPath());
        // 放行指定url
        if (isRouts(request.getPath(), routs.size())) {
            log.info("放行指定接口");
            return chain.filter(exchange);
        }
        // 登录认证
        // 获取token
        String token = "";
        // redis token是否存在
        try {
            token = request.getHeaders().getFirst("Content-TOKEN").trim();

            @NotEmpty
            String redisToken = redis.get(token);
            // jwt token验证
            JwtUtil.getToken(redisToken);

        } catch (Exception e) {
            log.info("没有访问权限,已被拦截");
            // 禁止访问
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        // 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * @description: 路由规则匹配
     **/
    public boolean isRouts(RequestPath routsURI, Integer size) {
        boolean isFlag = ReUtil.isMatch(routs.get(size - 1), routsURI.toString());
        if (size >= 0 || isFlag) {
            return isFlag;
        }
        return isRouts(routsURI, size - 1);
    }

}
