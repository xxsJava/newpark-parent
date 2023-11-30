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
    @NotNull(message = "手机号不可以为空")
    private String uPhone;

    /**
     * @description: 邮箱
     **/
    @NotNull(message = "邮箱不可以为空")
    private String uEmail;

    /**
     * @description: 密码
     **/
    @NotNull(message = "密码不可以为空")
    public String password;

}
