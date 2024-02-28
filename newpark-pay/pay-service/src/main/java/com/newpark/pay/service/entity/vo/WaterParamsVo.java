package com.newpark.pay.service.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/18 16:29
 **/
@Getter
@Setter
public class WaterParamsVo {

    /**
     * 交易类型 EXP|REV
     */
    @ApiModelProperty(value = "交易类型 EXP|REV")
    private String waterType;

    /**
     * 交易方式 ZFB|WX|ICONS
     */
    @ApiModelProperty(value = "交易方式 ZFB|WX|ICONS")
    private String waterManner;



}
