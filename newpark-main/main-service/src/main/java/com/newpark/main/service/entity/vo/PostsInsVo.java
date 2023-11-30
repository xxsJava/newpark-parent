package com.newpark.main.service.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.newpark.base.vali.ValidatedStrMsg;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.entity.PostsLabelDer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/16 16:17
 **/
@Getter
@Setter
public class PostsInsVo {

    @ApiModelProperty(value = "贴子id")
    @TableId(value = "t_id", type = IdType.AUTO)
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Long tId;

    @ApiModelProperty(value = "帖子标题")
    private String tTitle;

    @ApiModelProperty(value = "帖子内容")
    private String tContext;

    @ApiModelProperty(value = "帖子发布时间")
    @Positive(message = ValidatedStrMsg.DATE_TIME_MSG)
    private Long tPubTime;

    @ApiModelProperty(value = "帖子最近修改时间")
    @Positive(message = ValidatedStrMsg.DATE_TIME_MSG)
    private Long tLastTime;

    @ApiModelProperty(value = "帖子发布人")
    private Long tAuthorId;

    @ApiModelProperty(value = "帖子类型id 官方0/个人1")
    private Integer tTypeId;

    @ApiModelProperty(value = "学校id")
    @NotNull(message = ValidatedStrMsg.NOT_NULL_MSG)
    private Integer schoolId;

    /**
     * 帖子标签
     */
    private List<PostsLabelDer> labs;
}
