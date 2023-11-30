package com.newpark.main.service.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子表
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts")
@ApiModel(value="Posts对象", description="帖子表")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "贴子id")
    @TableId(value = "t_id", type = IdType.AUTO)
    private Long tId;

    @ApiModelProperty(value = "帖子标题")
    private String tTitle;

    @ApiModelProperty(value = "帖子内容")
    private String tContext;

    @ApiModelProperty(value = "帖子发布时间")
    private Long tPubTime;

    @ApiModelProperty(value = "帖子最近修改时间")
    private Long tLastTime;

    @ApiModelProperty(value = "帖子发布人")
    private Long tAuthorId;

    @ApiModelProperty(value = "帖子被点赞数量")
    private Long tLikeCount;

    @ApiModelProperty(value = "帖子被评论数量")
    private Long tComCount;

    @ApiModelProperty(value = "帖子转发数量")
    private Long tForwardCount;

    @ApiModelProperty(value = "帖子浏览数量")
    private Long tBrowseCount;

    @ApiModelProperty(value = "帖子类型id 官方/个人")
    private Integer tTypeId;

    @ApiModelProperty(value = "学校id")
    private Integer schoolId;

    /**
     * 帖子标签
     */
    private List<PostsLabel> labs;
}
