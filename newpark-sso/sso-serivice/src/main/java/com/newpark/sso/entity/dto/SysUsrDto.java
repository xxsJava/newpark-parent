package com.newpark.sso.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author xxs18
 * @Description 登录返回数据
 * @Date 2023/11/13 11:02
 **/
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value="SysUstDto实体", description="用户表DTO")
public class SysUsrDto implements Serializable {

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

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户详情信息")
    @TableField("info_id")
    private Long infoId;

    @ApiModelProperty(value = "用户会员信息")
    @TableField("vip_id")
    private Long vipId;
}
