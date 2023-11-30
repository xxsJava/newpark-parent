package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.PostsUsrLike;
import com.newpark.main.service.posts.mapper.PostsUsrLikeMapper;
import com.newpark.main.service.posts.service.IPostsUsrLikeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Service
public class PostsUsrLikeServiceImpl extends ServiceImpl<PostsUsrLikeMapper, PostsUsrLike> implements IPostsUsrLikeService {

}
