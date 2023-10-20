package com.newpark.sso.sys.entity;

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
 * 角色表
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableField("r_id")
    private Long rId;

    /**
     * 角色名字
     */
    @TableField("r_name")
    private String rName;

    /**
     * 角色描述
     */
    @TableField("r_desc")
    private String rDesc;

    /**
     * 权限类型 ALL|IM
     */
    @TableField("r_type")
    private String rType;

    /**
     * 创建时间
     */
    @TableField("r_create_time")
    private Integer rCreateTime;

    /**
     * 修改时间
     */
    @TableField("r_update_time")
    private Integer rUpdateTime;


}
