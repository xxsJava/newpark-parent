package com.newpark.sso.entity.vo;

import com.newpark.base.vali.ValidatedStrMsg;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @ApiModelProperty(value = "账号|手机号",required = true)
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = ValidatedStrMsg.PHONE_MSG)
    private String uPhone;

    /**
     * @description: 密码
     **/
    @ApiModelProperty(value = "密码",required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!@#$%^&*]{8,20}$" , message = ValidatedStrMsg.PASS_MSG)
    public String password;

}
