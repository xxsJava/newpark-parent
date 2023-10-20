package com.newpark.sso.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_routs")
public class SysRouts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    @TableField("rs_id")
    private Long rsId;

    /**
     * 权限名称
     */
    @TableField("rs_name")
    private String rsName;

    /**
     * 权限描述
     */
    @TableField("rs_desc")
    private String rsDesc;

    /**
     * 权限创建时间
     */
    @TableField("rs_create_time")
    private LocalDateTime rsCreateTime;

    /**
     * 权限修改时间
     */
    @TableField("rs_update_time")
    private LocalDateTime rsUpdateTime;

    /**
     * 权限类型
     */
    @TableField("rs_type")
    private String rsType;

    /**
     * 权限代码
     */
    @TableField("rs_code")
    private String rsCode;


}
