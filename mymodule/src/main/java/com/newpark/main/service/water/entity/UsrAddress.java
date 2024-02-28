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
 * 地址表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("usr_address")
@ApiModel(value="UsrAddress对象", description="地址表")
public class UsrAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址ID")
    @TableId(value = "addr_id", type = IdType.ID_WORKER)
    private Long addrId;

    @ApiModelProperty(value = "用户ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "收件人姓名")
    @TableField("recipient_name")
    private String recipientName;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "省")
    @TableField("state")
    private String state;

    @ApiModelProperty(value = "市")
    @TableField("city")
    private String city;

    @ApiModelProperty(value = "区、街道")
    @TableField("district")
    private String district;

    @ApiModelProperty(value = "详细地址")
    @TableField("full_addr")
    private String fullAddr;

    @ApiModelProperty(value = "地址标签")
    @TableField("addr_label")
    private String addrLabel;

    @ApiModelProperty(value = "是否默认地址")
    @TableField("is_default")
    private Boolean isDefault;


}
