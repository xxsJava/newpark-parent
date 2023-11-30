package com.newpark.sso.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户表")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "用户手机号")
    @TableField("u_phone")
    private String uPhone;

    @ApiModelProperty(value = "注册时间")
    @TableField("u_start_time")
    private Long uStartTime;

    @ApiModelProperty(value = "邮箱")
    @TableField("u_email")
    private String uEmail;

    @ApiModelProperty(value = "密码")
    @TableField("u_pass")
    private String uPass;

    @ApiModelProperty(value = "用户绑定的微信账户")
    @TableField("personal_we_chat")
    private String personalWeChat;

    @ApiModelProperty(value = "用户绑定的支付宝账户")
    @TableField("personal_pay")
    private String personalPay;


}
