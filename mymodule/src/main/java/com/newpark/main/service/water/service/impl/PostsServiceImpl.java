package com.newpark.main.service.water.service.impl;

import com.newpark.main.service.water.entity.Posts;
import com.newpark.main.service.water.mapper.PostsMapper;
import com.newpark.main.service.water.service.IPostsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 贴子表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {

}
