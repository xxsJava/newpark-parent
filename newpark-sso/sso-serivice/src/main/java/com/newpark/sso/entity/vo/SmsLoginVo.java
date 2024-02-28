package com.newpark.sso.entity.vo;/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/30 18:28
 **/

import javax.validation.constraints.Pattern;

import com.newpark.base.vali.ValidatedStrMsg;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description 验证码验证
 * @Date 2023/10/30 18:28
 **/
@Getter
@Setter
public class SmsLoginVo {

    /**
     * @description: 手机号
     **/
    @ApiModelProperty(value = "手机号",required = true)
    @Pattern(regexp = "^1[3-9]\\d{9}$",message = ValidatedStrMsg.PHONE_MSG)
    public String phone;

    /**
     * @description: 验证码
     **/
    @ApiModelProperty(value = "验证码")
    public String code;
}
