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
 * 收藏表
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("posts_collection")
@ApiModel(value="PostsCollection对象", description="收藏表")
public class PostsCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "收藏id")
    @TableId(value = "c_id", type = IdType.ID_WORKER)
    private Long cId;

    @ApiModelProperty(value = "被收藏id")
    @TableField("t_id")
    private Long tId;

    @ApiModelProperty(value = "收藏时间")
    @TableField("c_coll_time")
    private Long cCollTime;

    @ApiModelProperty(value = "收藏类型(帖子POSTS、消息MSG、ORDER商品)")
    @TableField("c_type")
    private String cType;

    @ApiModelProperty(value = "用户ID")
    @TableField("u_id")
    private Long uId;


}
