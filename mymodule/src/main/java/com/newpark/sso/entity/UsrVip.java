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
 * 会员表
 * </p>
 *
 * @author xxs18
 * @since 2023-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("usr_vip")
@ApiModel(value="UsrVip对象", description="会员表")
public class UsrVip implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员编号")
    @TableId(value = "v_id", type = IdType.ID_WORKER)
    private Long vId;

    @ApiModelProperty(value = "会员类型(未开通NOT、月MONTH、季度QUARTER、年YEAR)")
    @TableField("v_type")
    private String vType;

    @ApiModelProperty(value = "会员状态(未开通NOT、开通OPEN)")
    @TableField("v_status")
    private String vStatus;

    @ApiModelProperty(value = "会员等级")
    @TableField("v_level")
    private Integer vLevel;

    @ApiModelProperty(value = "会员积分")
    @TableField("v_points")
    private Integer vPoints;

    @ApiModelProperty(value = "会员开始时间")
    @TableField("v_start_time")
    private Long vStartTime;

    @ApiModelProperty(value = "会员结束时间")
    @TableField("v_end_time")
    private Long vEndTime;


}
