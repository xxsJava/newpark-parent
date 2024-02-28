package com.newpark.gateway.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.newpark.gateway.config.GatewayStaticRouts;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.redis.utils.RedisUtils;

import cn.hutool.core.util.ReUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("开始执行权限认证");
        // 获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应信息
        ServerHttpResponse response = exchange.getResponse();

        log.info("访问的接口---{}", request.getPath());
        // 放行指定url
        if (isRouts(request.getPath(), GatewayStaticRouts.ROUTS.size())) {
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
            // exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            // return exchange.getResponse().setComplete();
            return response.writeWith(Flux.just(setDataBuffer(request, response)));
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
    private boolean isRouts(RequestPath routsURI, Integer size) {
        boolean isFlag = ReUtil.isMatch(GatewayStaticRouts.ROUTS.get(size - 1), routsURI.toString());
        if (size >= 0 || isFlag) {
            return isFlag;
        }
        return isRouts(routsURI, size - 1);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 拦截返回响应数据提示
     * @param request
     * @param response
     * @return
     */
    @SneakyThrows
    private DataBuffer setDataBuffer(ServerHttpRequest request, ServerHttpResponse response) {
        // 如果需要返回响应数据，可以通过修改响应信息来实现
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        byte[] dataBytes = objectMapper.writeValueAsBytes(R.failed(ResponseCodeEnum.USR_NOT_FOUND));

        return response.bufferFactory().wrap(dataBytes);
    }

}
