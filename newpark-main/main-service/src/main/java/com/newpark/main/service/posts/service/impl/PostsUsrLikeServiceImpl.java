package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.PostsUsrLike;
import com.newpark.main.service.entity.vo.PostsUsrLikeVo;
import com.newpark.main.service.posts.mapper.PostsUsrLikeMapper;
import com.newpark.main.service.posts.service.IPostsUsrLikeService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private PostsUsrLikeMapper postsUsrLikeMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public Boolean postsUsrLikeIns(PostsUsrLike postsUsrLike) {
        postsUsrLike.setLikeId(idGeneratorSnowflake.snowflakeId());

        return postsUsrLikeMapper.insert(postsUsrLike) >= 1;
    }

    @Override
    public Boolean postsUsrLikeUpt(PostsUsrLikeVo postsUsrLikeVo) {
        UpdateWrapper<PostsUsrLike> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("like_id",postsUsrLikeVo.getLikeId());
        updateWrapper.set("like_type",postsUsrLikeVo.getLikeType());

        return postsUsrLikeMapper.update(null,updateWrapper) >= 0;
    }

    @Override
    public Boolean postsUsrLikeDel(Long likeId) {
        return postsUsrLikeMapper.deleteById(likeId) >= 0;
    }

    @Override
    public List<PostsUsrLike> postsUsrLikeFindAll(PageInfoVo pageInfoVo) {
        return null;
    }
}
