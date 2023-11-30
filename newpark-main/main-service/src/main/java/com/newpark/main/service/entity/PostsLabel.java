package com.newpark.main.service.entity;

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
 * 帖子标签
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_label")
@ApiModel(value="PostsLable对象", description="帖子标签")
public class PostsLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "lable_id", type = IdType.AUTO)
    private Long lableId;

    @ApiModelProperty(value = "帖子id")
    private Long postsId;

    @ApiModelProperty(value = "标签内容")
    private String lableText;


}
