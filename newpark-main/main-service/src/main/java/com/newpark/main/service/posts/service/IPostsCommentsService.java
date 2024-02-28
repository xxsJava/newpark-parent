package com.newpark.main.service.posts.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.main.service.entity.PostsComments;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
public interface IPostsCommentsService extends IService<PostsComments> {

    /**
     * 编辑帖子评论
     * @param postsComments
     * @return
     */
    Boolean setPostsComPut(PostsComments postsComments);

}
