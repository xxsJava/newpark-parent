package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.PostsComments;
import com.newpark.main.service.posts.mapper.PostsCommentsMapper;
import com.newpark.main.service.posts.service.IPostsCommentsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Service
public class PostsCommentsServiceImpl extends ServiceImpl<PostsCommentsMapper, PostsComments> implements IPostsCommentsService {

}
