package com.newpark.main.service.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newpark.base.vali.ValidatedStrMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author xxs18
 * @Description 收藏修改
 * @Date 2023/12/2 16:11
 **/
@Getter
@Setter
@TableName("posts_usr_like")
@ApiModel(value="PostsUsrLikeVo", description="点赞修改vo")
public class PostsUsrLikeVo {

    @NotNull
    @ApiModelProperty(value = "点赞id")
    @TableId(value = "like_id", type = IdType.AUTO)
    private Long likeId;

    @NotNull
    @ApiModelProperty(value = "1正常 2已删除")
    @TableId(value = "like_type", type = IdType.AUTO)
    @Min(value = 1 , message = ValidatedStrMsg.ERROR_MSG)
    @Max(value = 2 , message = ValidatedStrMsg.ERROR_MSG)
    private Integer likeType;

}
