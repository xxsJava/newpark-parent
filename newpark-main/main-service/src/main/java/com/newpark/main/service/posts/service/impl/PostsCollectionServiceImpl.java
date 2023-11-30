package com.newpark.main.service.posts.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.PostsCollection;
import com.newpark.main.service.posts.mapper.PostsCollectionMapper;
import com.newpark.main.service.posts.service.IPostsCollectionService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Service
public class PostsCollectionServiceImpl extends ServiceImpl<PostsCollectionMapper, PostsCollection> implements IPostsCollectionService {

    @Override
    public R<?> collectionFindAll(PageInfoVo pageInfoVo) {
        return R.ok();
    }

    @Override
    public R<?> collectionFindIns() {
        return R.ok();
    }

    @Override
    public R<?> collectionDel(Long coId) {
        return R.ok();
    }
}
