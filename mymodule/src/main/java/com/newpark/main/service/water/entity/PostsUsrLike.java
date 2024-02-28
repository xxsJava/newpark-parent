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
 * 点赞表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_usr_like")
@ApiModel(value="PostsUsrLike对象", description="点赞表")
public class PostsUsrLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点赞ID")
    @TableId(value = "like_id", type = IdType.ID_WORKER)
    private Long likeId;

    @ApiModelProperty(value = "帖子ID")
    @TableField("posts_id")
    private Long postsId;

    @ApiModelProperty(value = "评论ID")
    @TableField("com_id")
    private Long comId;

    @ApiModelProperty(value = "点赞时间")
    @TableField("like_time")
    private Long likeTime;

    @ApiModelProperty(value = "1帖子 2评论")
    @TableField("like_type")
    private String likeType;

    @ApiModelProperty(value = "点赞人ID")
    @TableField("u_id")
    private Long uId;


}
