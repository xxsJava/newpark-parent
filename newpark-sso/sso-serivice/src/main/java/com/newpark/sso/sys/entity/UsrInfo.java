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
 * 用户详情信息表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("usr_info")
@ApiModel(value="UsrInfo对象", description="用户详情信息表")
public class UsrInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "info_id", type = IdType.ID_WORKER)
    private Long infoId;

    @ApiModelProperty(value = "头像")
    @TableField("u_path")
    private String uPath;

    @ApiModelProperty(value = "用户昵称")
    @TableField("u_nikname")
    private String uNikname;

    @ApiModelProperty(value = "签名")
    @TableField("u_signature")
    private String uSignature;

    @ApiModelProperty(value = "学校ID")
    @TableField("school_id")
    private Integer schoolId;

    @ApiModelProperty(value = "专业")
    @TableField("u_specialty")
    private String uSpecialty;


}
