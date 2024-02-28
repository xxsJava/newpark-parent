package com.newpark.pay.service.entity.vo;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author xxs18
 * @Description 订单vo
 * @Date 2023/12/6 10:50
 **/
@Getter
@Setter
public class PayOrderVo {

    /**
     * 订单号
     */
    private String outTradeNo;

    /**
     * 订单总金额
     */
    @NotNull
    private Double totalAmount;

    /**
     * 订单标题
     */
    @NotEmpty
    private String subject;

    /**
     * 商品ID
     */
    @NonNull
    private Long productId;

}
