package com.newpark.im.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/23 10:13
 **/
@Getter
@Setter
@Component
public class ImConfig {

    /**
     * 请求地址
     */
    @Value("${openim.api}")
    private String api;

    /**
     * 获取秘钥
     */
    @Value("${openim.secret}")
    private String secret;

    /**
     * 管理员账号
     */
    @Value("${openim.adminAccount}")
    private String adminAccount;

    /**
     * 管理员账号密码
     */
    @Value("${openim.adminPwd}")
    private String adminPwd;

    /**
     * 预留请求头参数
     */
    @Value("${openim.authKey}")
    private String authKey;

}
