package com.newpark.main.service.posts.service;


import cn.shuibo.annotation.Encrypt;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.vo.PostParamVo;
import com.newpark.main.service.entity.vo.PostReviewParamVo;
import com.newpark.main.service.entity.vo.PostUptVo;
import com.newpark.main.service.entity.vo.PostsInsVo;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 帖子表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
public interface IPostsService extends IService<Posts> {

    /**
     * 帖子查询
     * @param pageInfoVo
     * @param postParamVo
     * @return
     */
    R getPostsFindAll(PageInfoVo pageInfoVo,PostParamVo postParamVo);

    /**
     * 查询帖子评论和回复
     * @param pageInfoVo
     * @param postReviewParamVo
     * @return
     */
    R postsReviewParentFindAll(PageInfoVo pageInfoVo, PostReviewParamVo postReviewParamVo);

    /**
     * 帖子编辑
     * @param postUpt
     * @return
     */
    R postsUpt(PostUptVo postUpt);

    /**
     * 删除帖子
     * @param tId
     * @return
     */
    R postsDel(Long tId);
}
