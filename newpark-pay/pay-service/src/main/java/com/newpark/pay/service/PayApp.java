package com.newpark.pay.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xxs18
 * @Description 支付启动类
 * @Date 2023/11/9 9:37
 **/

@SpringBootApplication
//@EnableFeignClients
public class PayApp {

    public static void main(String[] args) {
        SpringApplication.run(PayApp.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
