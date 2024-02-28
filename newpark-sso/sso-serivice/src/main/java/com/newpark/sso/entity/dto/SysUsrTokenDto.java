package com.newpark.sso.entity.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description
 * @Date 2024/1/2 15:08
 **/
@Getter
@Setter
public class SysUsrTokenDto {

    public static SysUsrTokenDto create(){
        return new SysUsrTokenDto();
    }

    /**
     * 用户token
     */
    private String usrToken;

    /**
     * 用户Id
     */
    private String uId;

}
