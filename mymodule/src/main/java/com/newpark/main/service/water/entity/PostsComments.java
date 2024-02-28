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
 * 评论表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_comments")
@ApiModel(value="PostsComments对象", description="评论表")
public class PostsComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论唯一id")
    @TableId(value = "com_id", type = IdType.AUTO)
    private Long comId;

    @ApiModelProperty(value = "帖子唯一id")
    @TableField("posts_id")
    private Long postsId;

    @ApiModelProperty(value = "评论内容")
    @TableField("com_content")
    private String comContent;

    @ApiModelProperty(value = "评论时间")
    @TableField("start_time")
    private Long startTime;

    @ApiModelProperty(value = "评论点赞数")
    @TableField("com_support")
    private Integer comSupport;

    @ApiModelProperty(value = "父级id 默认0")
    @TableField("com_parent_id")
    private Long comParentId;

    @ApiModelProperty(value = "评论人ID")
    @TableField("u_id")
    private Long uId;


}
