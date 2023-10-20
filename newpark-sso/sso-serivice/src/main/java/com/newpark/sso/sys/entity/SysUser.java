package com.newpark.sso.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    @TableId(value = "u_id", type = IdType.ID_WORKER)
    private Long uId;

    /**
     * 用户手机号
     */
    @TableField("u_phone")
    private String uPhone;

    /**
     * 注册时间
     */
    @TableField("u_start_time")
    private Long uStartTime;

    /**
     * 邮箱
     */
    @TableField("u_email")
    private String uEmail;

    /**
     * 密码
     */
    @TableField("u_pass")
    private String uPass;


}
