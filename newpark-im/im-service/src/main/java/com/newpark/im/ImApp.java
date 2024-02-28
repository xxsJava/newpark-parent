package com.newpark.im;

import com.newpark.base.enums.Buddha;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author xxs18
 * @Description 即时通讯启动类
 * @Date 2023/11/13 16:41
 **/
@SpringBootApplication
@MapperScan("com.newpark.im.mapper")
@EnableFeignClients
public class ImApp {

    public static void main(String[] args) {
        SpringApplication.run(ImApp.class,args);
        Buddha.getBuddha();
    }

}
