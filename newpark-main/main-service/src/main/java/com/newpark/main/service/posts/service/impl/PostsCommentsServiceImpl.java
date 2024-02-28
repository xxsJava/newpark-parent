package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.entity.PostsComments;
import com.newpark.main.service.posts.mapper.PostsCommentsMapper;
import com.newpark.main.service.posts.service.IPostsCommentsService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private PostsCommentsMapper postsCommentsMapper;

    @Override
    public Boolean setPostsComPut(PostsComments postsComments) {
        UpdateWrapper<PostsComments> updateWrap = new UpdateWrapper<>();
        updateWrap.set(StringUtils.isNotEmpty(postsComments.getComContent()),"com_content",postsComments.getComContent());
        updateWrap.set(StringUtils.isNotNull(postsComments.getComSupport()),"com_support",postsComments.getComSupport());
        updateWrap.eq(StringUtils.isNotNull(postsComments.getPostsId()),"posts_id",postsComments.getPostsId());
        updateWrap.eq(StringUtils.isNotNull(postsComments.getComId()),"com_id",postsComments.getComId());

        return postsCommentsMapper.update(null,updateWrap) >= 1;
    }
}
