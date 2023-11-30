package com.newpark.main.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_usr_like")
@ApiModel(value="PostsUsrLike对象", description="点赞表")
public class PostsUsrLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "点赞id")
    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer likeId;

    @ApiModelProperty(value = "贴子id")
    private Integer postsId;

    @ApiModelProperty(value = "评论id")
    private Integer comId;

    @ApiModelProperty(value = "点赞时间")
    private LocalDateTime likeTime;

    @ApiModelProperty(value = "1帖子 2评论")
    private Integer likeType;

    @ApiModelProperty(value = "用户id")
    private Integer uId;


}
