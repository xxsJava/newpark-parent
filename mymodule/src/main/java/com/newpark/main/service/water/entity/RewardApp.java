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
 * 接受悬赏表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("reward_app")
@ApiModel(value="RewardApp对象", description="接受悬赏表")
public class RewardApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申请表编号")
    @TableId(value = "app_id", type = IdType.ID_WORKER)
    private Long appId;

    @ApiModelProperty(value = "申请人编号")
    @TableField("task_id")
    private Long taskId;

    @ApiModelProperty(value = "申请时间")
    @TableField("app_time")
    private Long appTime;

    @ApiModelProperty(value = "悬赏任务编号")
    @TableField("r_id")
    private Long rId;

    @ApiModelProperty(value = "完成时间")
    @TableField("succ_time")
    private Long succTime;


}
