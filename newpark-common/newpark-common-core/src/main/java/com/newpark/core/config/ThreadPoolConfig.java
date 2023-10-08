package com.newpark.core.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfig {


    @Bean("receiveExecutor")
    public ThreadPoolExecutor receiveExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("receive-pool-%d").build();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(
                availableProcessors * 10,
                availableProcessors * 20,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(400), threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
