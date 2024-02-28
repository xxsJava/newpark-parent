package com.newpark.pay.service.entity.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author xxs18
 * @Description 接收支付完的响应
 * @Date 2023/12/6 11:09
 **/
@Getter
@Setter
public class AppPayResultVo {

    /**
     * 响应码
     */
    @NotEmpty
    @Min(value = 10000)
    @Max(value = 10000)
    private String code;

    /**
     * 响应信息
     */
    @NotEmpty
    private String msg;

    /**
     * appID
     */
    @NotEmpty
    private String appId;

    /**
     * 授权appID
     */
    @NotEmpty
    private String authAppId;

    /**
     * 编码
     */
    @NotEmpty
    private String charset;

    /**
     * 支付时间
     */
    @NotEmpty
    private String timestamp;

    /**
     * 订单号
     */
    @NotEmpty
    private String outTradeNo;

    /**
     * 订单总金额
     */
    @NotNull
    private Double totalAmount;

    /**
     * 订单原始ID
     */
    @NotEmpty
    private String tradeNo;

    /**
     * 收款人ID
     */
    @NotEmpty
    private String sellerId;
}
