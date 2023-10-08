package com.newpark.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author xxs18
 * @since 2023-10-08
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
    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uId;

    /**
     * 用户名
     */
    @TableField("u_name")
    private String uName;

    /**
     * 用户密码
     */
    @TableField("u_pass")
    private String uPass;

    /**
     * 用户头像
     */
    @TableField("u_icon")
    private String uIcon;

    /**
     * 用户性别
     */
    @TableField("u_sex")
    private String uSex;

    /**
     * 用户角色
     */
    @TableField("u_role_id")
    private Integer uRoleId;


}
