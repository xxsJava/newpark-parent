package com.newpark.sso.entity;

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
 * 角色表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="角色表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色编号")
    @TableId(value = "r_id", type = IdType.AUTO)
    private Long rId;

    @ApiModelProperty(value = "角色名字")
    @TableField("r_name")
    private String rName;

    @ApiModelProperty(value = "角色描述")
    @TableField("r_desc")
    private String rDesc;

    @ApiModelProperty(value = "角色类型 ALL|IM")
    @TableField("r_type")
    private String rType;

    @ApiModelProperty(value = "创建时间")
    @TableField("r_create_time")
    private Long rCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("r_update_time")
    private Long rUpdateTime;


}
