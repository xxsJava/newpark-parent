package com.newpark.sso.entity.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.hutool.crypto.digest.MD5;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description 创建用户
 * @Date 2023/11/20 10:01
 **/
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value="SysUserVo对象", description="用户表")
public class SysUsrVo implements Serializable {

    @ApiModelProperty(value = "用户编号")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "用户手机号")
    @TableField("u_phone")
    private String uPhone;

    @ApiModelProperty(value = "注册时间")
    @TableField("u_start_time")
    private Long uStartTime;

    @ApiModelProperty(value = "密码")
    @TableField("u_pass")
    private String uPass;

    public void setuPass(String uPass) {
        this.uPass = MD5.create().digestHex16(uPass.trim());
    }
}
