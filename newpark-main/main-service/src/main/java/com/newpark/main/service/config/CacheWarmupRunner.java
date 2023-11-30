package com.newpark.main.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author xxs18
 * @Description Main服务redis预热加载
 * @Date 2023/11/24 15:28
 **/
@Slf4j
@Component
public class CacheWarmupRunner implements CommandLineRunner {

    @Resource
    private CacheWarmupService cacheWarmupService;

    @Override
    public void run(String... args) throws Exception {
        log.info("redis开始预热");
        cacheWarmupService.warmup();
    }
}
