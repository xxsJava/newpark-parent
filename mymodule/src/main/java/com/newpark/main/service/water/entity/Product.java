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
 * 商品表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
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
    @TableField("p_name")
    private String pName;

    @ApiModelProperty(value = "商品描述")
    @TableField("p_desc")
    private String pDesc;

    @ApiModelProperty(value = "商品图片")
    @TableField("p_imgs")
    private String pImgs;

    @ApiModelProperty(value = "商品价格")
    @TableField("p_price")
    private Double pPrice;

    @ApiModelProperty(value = "商品数量")
    @TableField("p_num")
    private Integer pNum;

    @ApiModelProperty(value = "商品发布人ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "商品状态(审核AUDIT、待售FORSALE、已售SOLD、下架TOTH)")
    @TableField("p_status")
    private String pStatus;

    @ApiModelProperty(value = "商品发布时间")
    @TableField("p_pub_time")
    private Long pPubTime;

    @ApiModelProperty(value = "商品备注")
    @TableField("p_other")
    private String pOther;


}
