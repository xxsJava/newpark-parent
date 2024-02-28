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
 * 支付流水表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("usr_water")
@ApiModel(value="UsrWater对象", description="支付流水表")
public class UsrWater implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流水ID")
    @TableId(value = "water_id", type = IdType.ID_WORKER)
    private Long waterId;

    @ApiModelProperty(value = "用户ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "交易方式(支付宝ZFB,微信WX,新园币ICONS)")
    @TableField("water_manner")
    private String waterManner;

    @ApiModelProperty(value = "订单号、交易号")
    @TableField("water_order")
    private Long waterOrder;

    @ApiModelProperty(value = "交易类型(支出EXP、收入REV)")
    @TableField("water_type")
    private String waterType;

    @ApiModelProperty(value = "对方账户ID")
    @TableField("outside_u_id")
    private Long outsideUId;

    @ApiModelProperty(value = "支付的金额")
    @TableField("water_monye")
    private Double waterMonye;

    @ApiModelProperty(value = "余额")
    @TableField("water_balance")
    private Double waterBalance;

    @ApiModelProperty(value = "支付时间")
    @TableField("water_time")
    private Long waterTime;

    @ApiModelProperty(value = "备注")
    @TableField("water_desc")
    private String waterDesc;


}
