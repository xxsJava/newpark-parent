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
 * 举报表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("report")
@ApiModel(value="Report对象", description="举报表")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "举报ID")
    @TableId(value = "report_id", type = IdType.ID_WORKER)
    private Long reportId;

    @ApiModelProperty(value = "举报人ID")
    @TableField("u_id")
    private Long uId;

    @ApiModelProperty(value = "被举报用户ID")
    @TableField("reported_u_id")
    private Long reportedUId;

    @ApiModelProperty(value = "被举报商品、悬赏ID")
    @TableField("product_id")
    private Long productId;

    @ApiModelProperty(value = "举报原因")
    @TableField("report_reason")
    private String reportReason;

    @ApiModelProperty(value = "举报图片")
    @TableField("report_imgs")
    private String reportImgs;

    @ApiModelProperty(value = "举报时间")
    @TableField("report_time")
    private Long reportTime;

    @ApiModelProperty(value = "举报状态 (待处理、已处理)")
    @TableField("status")
    private String status;


}
