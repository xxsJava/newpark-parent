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
 * 贴子表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts")
@ApiModel(value="Posts对象", description="贴子表")
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "贴子id")
    @TableId(value = "t_id", type = IdType.ID_WORKER)
    private Long tId;

    @ApiModelProperty(value = "帖子标题")
    @TableField("t_title")
    private String tTitle;

    @ApiModelProperty(value = "帖子内容["text",'img','img','text']")
    @TableField("t_context")
    private String tContext;

    @ApiModelProperty(value = "帖子发布时间")
    @TableField("t_pub_time")
    private Long tPubTime;

    @ApiModelProperty(value = "帖子最近修改时间")
    @TableField("t_last_time")
    private Long tLastTime;

    @ApiModelProperty(value = "帖子发布人")
    @TableField("t_author_id")
    private Long tAuthorId;

    @ApiModelProperty(value = "帖子被点赞数量")
    @TableField("t_like_count")
    private Integer tLikeCount;

    @ApiModelProperty(value = "帖子转发数量")
    @TableField("t_forward_count")
    private Integer tForwardCount;

    @ApiModelProperty(value = "帖子类型id 官方(PUB)/个人(PRI)")
    @TableField("t_type_id")
    private String tTypeId;

    @ApiModelProperty(value = "帖子被评论数量")
    @TableField("t_com_count")
    private Integer tComCount;

    @ApiModelProperty(value = "学校编号")
    @TableField("school_id")
    private Integer schoolId;

    @ApiModelProperty(value = "帖子浏览数量")
    @TableField("t_browse_count")
    private Integer tBrowseCount;


}
