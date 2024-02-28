package com.newpark.main.service.posts.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.PostsCollection;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
public interface IPostsCollectionService extends IService<PostsCollection> {

    List<Object> collectionFindAll(PageInfoVo pageInfoVo,Long uId);

    Boolean collectionFindIns(PostsCollection postsCollection);

    Boolean collectionDel(Long coId);

}
