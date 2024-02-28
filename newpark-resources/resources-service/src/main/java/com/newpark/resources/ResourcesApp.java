package com.newpark.resources;

import com.newpark.base.enums.Buddha;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xxs18
 * @Description 资源服务启动类
 * @Date 2023/11/29 15:15
 **/
@SpringBootApplication
public class ResourcesApp {

    public static void main(String[] args) {
        SpringApplication.run(ResourcesApp.class,args);
        Buddha.getBuddha();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
