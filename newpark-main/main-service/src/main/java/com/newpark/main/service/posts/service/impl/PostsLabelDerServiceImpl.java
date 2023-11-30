package com.newpark.main.service.posts.service.impl;

import com.github.yulichang.query.MPJQueryWrapper;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.entity.PostsLabelDer;
import com.newpark.main.service.entity.dto.PostsDto;
import com.newpark.main.service.entity.vo.PostsInsVo;
import com.newpark.main.service.posts.mapper.PostsLabelDerMapper;
import com.newpark.main.service.posts.mapper.PostsLabelMapper;
import com.newpark.main.service.posts.mapper.PostsMapper;
import com.newpark.main.service.posts.service.IPostsLabelDerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 帖子标签分配表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-16
 */
@Service
public class PostsLabelDerServiceImpl extends ServiceImpl<PostsLabelDerMapper, PostsLabelDer> implements IPostsLabelDerService {

    @Resource
    private PostsMapper postsMapper;

    /**
     * 1. 帖子发布
     * 2. 标签新增
     * @param postInsVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R postsIns(PostsInsVo postInsVo) {
        //帖子新增
        postsMapper.postsIns(postInsVo);
        return R.ok(saveBatch(postInsVo.getLabs(),postInsVo.getLabs().size()));
    }



}
