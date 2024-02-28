package com.newpark.pay.service;

import com.newpark.base.enums.Buddha;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xxs18
 * @Description 支付启动类
 * @Date 2023/11/9 9:37
 **/

@SpringBootApplication
@MapperScan("com.newpark.pay.service.mapper")
//@EnableFeignClients
public class PayApp {

    public static void main(String[] args) {
        SpringApplication.run(PayApp.class, args);
        Buddha.getBuddha();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
