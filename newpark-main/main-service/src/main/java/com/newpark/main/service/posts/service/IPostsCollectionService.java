package com.newpark.main.service.posts.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.PostsCollection;
import com.newpark.pojo.vo.PageInfoVo;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
public interface IPostsCollectionService extends IService<PostsCollection> {

    R<?> collectionFindAll(PageInfoVo pageInfoVo);

    R<?> collectionFindIns();

    R<?> collectionDel(Long coId);

}
