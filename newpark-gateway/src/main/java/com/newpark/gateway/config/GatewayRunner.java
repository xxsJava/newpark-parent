package com.newpark.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author xxs18
 * @Description 路由预加载
 * @Date 2023/12/21 10:16
 **/
@Slf4j
@Component
public class GatewayRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        log.info("-----------》开始加载路由");
        GatewayStaticRouts.ROUTS.add("/usr/.*");
        log.info("-----------》路由加载完毕");
    }

}
