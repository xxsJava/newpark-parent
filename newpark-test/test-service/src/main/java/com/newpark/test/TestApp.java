package com.newpark.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xxs18
 * @Description 测试类
 * @Date 2023/10/8 10:29
 **/
@SpringBootApplication
@MapperScan("com.newpark.test.mapper")
public class TestApp {
    public static void main(String[] args) {
        SpringApplication.run(TestApp.class,args);
    }
}
