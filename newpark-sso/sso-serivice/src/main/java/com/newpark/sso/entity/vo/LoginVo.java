package com.newpark.sso.entity.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author xxs18
 * @Description 登录Vo
 * @Date 2023/10/9 15:00
 **/
@Getter
@Setter
public class LoginVo {


    /**
     * @description: 手机号
     **/
    private String uPhone;

    /**
     * @description: 邮箱
     **/
    private String uEmail;

    /**
     * @description: 密码
     **/
    public String password;

}
