package com.newpark.main.service.entity.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newpark.base.vali.ValidatedStrMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * @Author xxs18
 * @Description 商品查询条件
 * @Date 2023/12/1 14:00
 **/
@Getter
@Setter
@TableName("product")
@ApiModel(value="ProductParamDto对象", description="商品条件参数")
public class ProductParamVo {

    @ApiModelProperty(value = "商品状态(审核AUDIT、待售FORSALE、已售SOLD、下架TOTH)",required = true)
    private String pStatus;

    @ApiModelProperty(value = "价格排序(升序ASC 降序DESC)",required = true)
    @Pattern(regexp = "^(?i)(ASC|DESC)$",message = ValidatedStrMsg.ERROR_MSG)
    private String priceSort;

    @ApiModelProperty(value = "发布时间排序(升序ASC 降序DESC)",required = true)
    @Pattern(regexp = "^(?i)(ASC|DESC)$",message = ValidatedStrMsg.ERROR_MSG)
    private String timeSort;

}
