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
 * 评价表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rating")
@ApiModel(value="Rating对象", description="评价表")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价ID")
    @TableId(value = "ra_id", type = IdType.ID_WORKER)
    private Integer raId;

    @ApiModelProperty(value = "发布人ID")
    @TableField("pub_id")
    private Integer pubId;

    @ApiModelProperty(value = "完成者ID")
    @TableField("succ_id")
    private Integer succId;

    @ApiModelProperty(value = "评分(1-5)")
    @TableField("ra_score")
    private Integer raScore;

    @ApiModelProperty(value = "评论")
    @TableField("ra_com")
    private String raCom;


}
