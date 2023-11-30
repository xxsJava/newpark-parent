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
 * 用户分配表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_divide")
@ApiModel(value="SysRoleDivide对象", description="用户分配表")
public class SysRoleDivide implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "u_id", type = IdType.ID_WORKER)
    private Long uId;

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
