package com.newpark.sso;

import cn.shuibo.annotation.EnableSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * @Author xxs18
 * @Description sso单点登录启动器
 * @Date 2023/10/9 15:11
 **/
@SpringBootApplication
@MapperScan("com.newpark.sso.sys.mapper")
@EnableSecurity
@EnableTransactionManagement
public class SsoApp {
    public static void main(String[] args) {
        SpringApplication.run(SsoApp.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
