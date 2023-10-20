package com.newpark.sso.config;

import com.newpark.sso.filter.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author xxs18
 * @Description 登录拦截器
 * @Date 2023/10/9 16:34
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JWTInterceptor())
//                .addPathPatterns("/usr/login")
//                .excludePathPatterns("");
    }

}
