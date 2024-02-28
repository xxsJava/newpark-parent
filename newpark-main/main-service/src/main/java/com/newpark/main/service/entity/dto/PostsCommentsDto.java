package com.newpark.main.service.entity.dto;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.newpark.main.service.entity.PostsComments;
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
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_comments")
@ApiModel(value="PostsComments对象", description="评论表")
public class PostsCommentsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论唯一id")
    @TableId(value = "com_id", type = IdType.AUTO)
    private Long comId;

    @ApiModelProperty(value = "帖子唯一id")
    private Integer postsId;

    @ApiModelProperty(value = "用户id")
    private Integer uId;

    @ApiModelProperty(value = "头像")
    @TableField("u_path")
    private String uPath;

    @ApiModelProperty(value = "用户昵称")
    @TableField("u_nikname")
    private String uNikname;

    @ApiModelProperty(value = "评论内容")
    private String comContent;

    @ApiModelProperty(value = "评论时间")
    private Long startTime;

    @ApiModelProperty(value = "评论点赞数")
    private Integer comSupport;

    @ApiModelProperty(value = "0 评论 | 回复")
    private Long comParentId;


    private List<PostsComments> coms;
}
