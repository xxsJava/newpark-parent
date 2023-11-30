package com.newpark.main.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.newpark.base.vali.ValidatedStrMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
public class PostsComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论唯一id")
    @TableId(value = "com_id", type = IdType.AUTO)
    private Long comId;

    @ApiModelProperty(value = "帖子唯一id")
    private Long postsId;

    @ApiModelProperty(value = "用户id")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Long uId;

    @ApiModelProperty(value = "评论内容")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private String comContent;

    @ApiModelProperty(value = "评论时间")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Long startTime;

    @ApiModelProperty(value = "评论点赞数")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Integer comSupport;

    @ApiModelProperty(value = "0 评论 | 回复")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Long comParentId;

}
