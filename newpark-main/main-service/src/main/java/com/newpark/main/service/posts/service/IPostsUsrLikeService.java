package com.newpark.main.service.posts.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.main.service.entity.PostsUsrLike;
import com.newpark.main.service.entity.vo.PostsUsrLikeVo;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 点赞表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
public interface IPostsUsrLikeService extends IService<PostsUsrLike> {

    Boolean postsUsrLikeIns(PostsUsrLike postsUsrLike);

    Boolean postsUsrLikeUpt(PostsUsrLikeVo postsUsrLikeVo);

    Boolean postsUsrLikeDel(Long likeId);

    List<PostsUsrLike> postsUsrLikeFindAll(PageInfoVo pageInfoVo);
}
