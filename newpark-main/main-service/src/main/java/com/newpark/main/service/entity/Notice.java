package com.newpark.main.service.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 通知表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("notice")
@ApiModel(value="Notice对象", description="通知表")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知ID")
    @TableId(value = "no_id", type = IdType.ID_WORKER)
    private Long noId;

    @ApiModelProperty(value = "接收通知的用户ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "通知内容")
    @TableField("no_content")
    private String noContent;

    @ApiModelProperty(value = "通知是否已读")
    @TableField("is_read")
    private Boolean isRead;

    @ApiModelProperty(value = "通知创建时间")
    @TableField("created_time")
    private Long createdTime;


}
