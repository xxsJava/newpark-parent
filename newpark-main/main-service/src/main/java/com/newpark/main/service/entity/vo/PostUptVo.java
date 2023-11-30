package com.newpark.main.service.entity.vo;

import javax.validation.constraints.Positive;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newpark.base.vali.ValidatedStrMsg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/16 17:56
 **/
@Getter
@Setter
@TableName("posts")
@ApiModel(value="Posts修改对象", description="帖子表")
public class PostUptVo {

    @ApiModelProperty(value = "贴子id")
    @TableId(value = "t_id", type = IdType.AUTO)
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tId;

    @ApiModelProperty(value = "帖子标题")
    private String tTitle;

    @ApiModelProperty(value = "帖子内容")
    private String tContext;

    @ApiModelProperty(value = "帖子最近修改时间")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tLastTime;

    @ApiModelProperty(value = "帖子被点赞数量")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tLikeCount;

    @ApiModelProperty(value = "帖子被评论数量")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tComCount;

    @ApiModelProperty(value = "帖子转发数量")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tForwardCount;

    @ApiModelProperty(value = "帖子浏览数量")
    @Positive(message = ValidatedStrMsg.ERROR_MSG)
    private Long tBrowseCount;
}
