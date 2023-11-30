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
 * 商品表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
@ApiModel(value="Product对象", description="商品表")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "p_id", type = IdType.ID_WORKER)
    private Long pId;

    @ApiModelProperty(value = "商品名称")
    private String pName;

    @ApiModelProperty(value = "商品描述")
    private String pDesc;

    @ApiModelProperty(value = "商品图片")
    private String pImgs;

    @ApiModelProperty(value = "商品价格")
    private Double pPrice;

    @ApiModelProperty(value = "商品发布人ID")
    private Long uId;

    @ApiModelProperty(value = "商品状态(审核AUDIT、待售FORSALE、已售SOLD、下架TOTH)")
    private String pStatus;

    @ApiModelProperty(value = "商品发布时间")
    private Long pPubTime;

    @ApiModelProperty(value = "商品备注")
    private String pOthe;


}
