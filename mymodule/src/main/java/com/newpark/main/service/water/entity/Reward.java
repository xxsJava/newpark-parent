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
 * 悬赏表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("reward")
@ApiModel(value="Reward对象", description="悬赏表")
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "悬赏任务id")
    @TableId(value = "r_id", type = IdType.ID_WORKER)
    private Long rId;

    @ApiModelProperty(value = "发布人id")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "发布时间")
    @TableField("start_time")
    private Long startTime;

    @ApiModelProperty(value = "截止时间")
    @TableField("end_time")
    private Long endTime;

    @ApiModelProperty(value = "悬赏标题")
    @TableField("r_title")
    private String rTitle;

    @ApiModelProperty(value = "悬赏描述")
    @TableField("r_desc")
    private String rDesc;

    @ApiModelProperty(value = "悬赏金额RMB")
    @TableField("r_money")
    private Double rMoney;

    @ApiModelProperty(value = "描述图片")
    @TableField("r_imgs")
    private String rImgs;


}
