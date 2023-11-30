package com.newpark.main.service.product.entity;

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
 * @since 2023-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product_transaction")
@ApiModel(value="ProductTransaction对象", description="商品订单表")
public class ProductTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "交易ID")
    @TableId(value = "tr_id", type = IdType.ID_WORKER)
    private Long trId;

    @ApiModelProperty(value = "买方ID")
    private Long buyId;

    @ApiModelProperty(value = "卖方ID")
    private Long selleId;

    @ApiModelProperty(value = "商品ID")
    private Long proId;

    @ApiModelProperty(value = "交易时间")
    private Long tTime;

    @ApiModelProperty(value = "交易金额")
    private Double tMoney;

    @ApiModelProperty(value = "交易状态(待付款、已付款、已发货)")
    private String tStatus;


}
