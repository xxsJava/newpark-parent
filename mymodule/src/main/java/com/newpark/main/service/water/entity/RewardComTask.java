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
 * 悬赏完成表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("reward_com_task")
@ApiModel(value="RewardComTask对象", description="悬赏完成表")
public class RewardComTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "完成表编号")
    @TableId(value = "r_com_id", type = IdType.ID_WORKER)
    private Integer rComId;

    @ApiModelProperty(value = "申请人编号")
    @TableField("u_id")
    private Integer uId;

    @ApiModelProperty(value = "完成时间时间")
    @TableField("end_time")
    private Long endTime;

    @ApiModelProperty(value = "悬赏任务编号")
    @TableField("r_id")
    private Integer rId;

    @ApiModelProperty(value = "完成证明")
    @TableField("r_proof")
    private String rProof;


}
