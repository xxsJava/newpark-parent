package com.newpark.sso.entity.vo;/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/30 18:28
 **/

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
    public String phone;

    /**
     * @description: 验证码
     **/
    public String code;
}
