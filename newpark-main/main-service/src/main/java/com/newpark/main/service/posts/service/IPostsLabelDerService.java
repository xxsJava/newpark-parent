package com.newpark.main.service.posts.service;

import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.entity.PostsLabelDer;
import com.newpark.main.service.entity.dto.PostsDto;
import com.newpark.main.service.entity.vo.PostsInsVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子标签分配表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-16
 */
public interface IPostsLabelDerService extends IService<PostsLabelDer> {

    R postsIns(PostsInsVo postInsVo);

}
