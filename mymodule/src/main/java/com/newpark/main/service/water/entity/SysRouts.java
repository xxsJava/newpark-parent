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
 * 权限表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_routs")
@ApiModel(value="SysRouts对象", description="权限表")
public class SysRouts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限编号")
    @TableId(value = "rs_id", type = IdType.ID_WORKER)
    private Long rsId;

    @ApiModelProperty(value = "权限名称")
    @TableField("rs_name")
    private String rsName;

    @ApiModelProperty(value = "权限描述")
    @TableField("rs_desc")
    private String rsDesc;

    @ApiModelProperty(value = "权限创建时间")
    @TableField("rs_create_time")
    private Long rsCreateTime;

    @ApiModelProperty(value = "权限修改时间")
    @TableField("rs_update_time")
    private Long rsUpdateTime;

    @ApiModelProperty(value = "权限类型(URL|USR)")
    @TableField("rs_type")
    private String rsType;

    @ApiModelProperty(value = "权限代码")
    @TableField("rs_code")
    private String rsCode;

    @ApiModelProperty(value = "权限类名")
    @TableField("rs_class_name")
    private String rsClassName;

    @ApiModelProperty(value = "权限方法名")
    @TableField("rs_method")
    private String rsMethod;

    @ApiModelProperty(value = "权限请求类型")
    @TableField("rs_req_type")
    private String rsReqType;

    @ApiModelProperty(value = "请求URL")
    @TableField("rs_req_url")
    private String rsReqUrl;


}
