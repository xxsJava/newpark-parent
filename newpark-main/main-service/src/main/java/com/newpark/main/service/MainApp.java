package com.newpark.main.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xxs18
 * @Description 用户通用功能服务启动类
 * @Date 2023/10/23 9:32
 **/
@SpringBootApplication
@MapperScan("com.newpark.main.service.*.mapper")
@EnableFeignClients(basePackages = {"com.newpark.pay"})
@EnableTransactionManagement
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
