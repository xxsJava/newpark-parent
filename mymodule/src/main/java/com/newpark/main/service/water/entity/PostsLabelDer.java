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
 * 帖子标签分配表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_label_der")
@ApiModel(value="PostsLabelDer对象", description="帖子标签分配表")
public class PostsLabelDer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一id")
    @TableId(value = "der_id", type = IdType.AUTO)
    private Long derId;

    @ApiModelProperty(value = "帖子id")
    @TableField("posts_id")
    private Long postsId;

    @ApiModelProperty(value = "标签id")
    @TableField("lab_id")
    private Long labId;


}
