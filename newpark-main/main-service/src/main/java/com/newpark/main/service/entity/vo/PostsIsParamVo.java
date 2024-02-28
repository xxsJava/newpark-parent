package com.newpark.main.service.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description 帖子数据查询条件
 * @Date 2023/12/1 17:33
 **/
@Getter
@Setter
public class PostsIsParamVo {

    /**
     * 帖子Id
     * 1. 查帖子详情
     */
    private Long postId;

    /**
     * 用户Id
     * 1. 查用户收藏的帖子
     * 2. 查用户发布的帖子
     */
    private Long usrId;

}
