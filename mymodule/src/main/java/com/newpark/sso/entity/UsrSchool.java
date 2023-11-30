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
 * 校区
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("usr_school")
@ApiModel(value="UsrSchool对象", description="校区")
public class UsrSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "校区id")
    @TableId(value = "school_id", type = IdType.AUTO)
    private Integer schoolId;

    @ApiModelProperty(value = "校区名称")
    @TableField("school_name")
    private String schoolName;

    @ApiModelProperty(value = "排序字段")
    @TableField("seq")
    private Integer seq;


}
