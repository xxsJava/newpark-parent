package com.newpark.main.service.water.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品订单表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orders")
@ApiModel(value="Orders对象", description="商品订单表")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易ID")
    @TableId(value = "tr_id", type = IdType.ID_WORKER)
    private String trId;

    @ApiModelProperty(value = "买方ID")
    @TableField("buy_id")
    private Long buyId;

    @ApiModelProperty(value = "卖方ID")
    @TableField("selle_id")
    private Long selleId;

    @ApiModelProperty(value = "交易物ID")
    @TableField("pro_id")
    private Long proId;

    @ApiModelProperty(value = "交易时间")
    @TableField("t_time")
    private Long tTime;

    @ApiModelProperty(value = "交易金额")
    @TableField("t_money")
    private Double tMoney;

    @ApiModelProperty(value = "交易状态(待付款PEN_PAY、已付款PAID、已完成DONE)")
    @TableField("t_status")
    private String tStatus;

    @ApiModelProperty(value = "订单类型(商品ORDER、跑腿RUNS)")
    @TableField("tr_type")
    private String trType;

    @ApiModelProperty(value = "交易方式(ZFB支付宝|WX微信|NG内购)")
    @TableField("tr_manner")
    private String trManner;

    @ApiModelProperty(value = "交易原始ID")
    @TableField("tr_init")
    private Long trInit;


}
