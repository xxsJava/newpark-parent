package com.newpark.sso;

import cn.shuibo.annotation.EnableSecurity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xxs18
 * @Description sso单点登录启动器
 * @Date 2023/10/9 15:11
 **/
@SpringBootApplication
@MapperScan("com.newpark.sso.sys.mapper")
@EnableSecurity
public class SsoApp {
    public static void main(String[] args) {
        SpringApplication.run(SsoApp.class,args);
    }

}
