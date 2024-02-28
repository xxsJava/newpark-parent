package com.newpark.main.service.entity.vo;

import java.io.Serializable;

import javax.validation.constraints.Positive;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newpark.base.vali.ValidatedStrMsg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/1 16:06
 **/
@Getter
@Setter
@TableName("product")
@ApiModel(value="ProductUptDto对象", description="商品修改")
public class ProductUptVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID",required = true)
    @TableId(value = "p_id", type = IdType.ID_WORKER)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long pId;

    @ApiModelProperty(value = "商品名称",required = true)
    private String pName;

    @ApiModelProperty(value = "商品描述",required = true)
    private String pDesc;

    @ApiModelProperty(value = "商品图片",required = true)
    private String pImgs;

    @ApiModelProperty(value = "商品价格",required = true)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Double pPrice;

    @ApiModelProperty(value = "商品备注")
    private String pOther;
}
