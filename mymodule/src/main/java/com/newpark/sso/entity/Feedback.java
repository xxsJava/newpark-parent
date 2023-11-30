package com.newpark.sso.entity;

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
 * 反馈表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("feedback")
@ApiModel(value="Feedback对象", description="反馈表")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "反馈ID")
    @TableId(value = "feedback_id", type = IdType.ID_WORKER)
    private Long feedbackId;

    @ApiModelProperty(value = "用户ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "反馈内容")
    @TableField("feedback_content")
    private String feedbackContent;

    @ApiModelProperty(value = "反馈图片")
    @TableField("feedback_imgs")
    private String feedbackImgs;

    @ApiModelProperty(value = "反馈时间")
    @TableField("feedback_time")
    private Long feedbackTime;

    @ApiModelProperty(value = "反馈状态 (待处理、已处理)")
    @TableField("status")
    private String status;


}
