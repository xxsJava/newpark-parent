package com.newpark.main.service.posts.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newpark.main.service.entity.PostsComments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Mapper
public interface PostsCommentsMapper extends BaseMapper<PostsComments> {


    List<PostsComments> collectionFindAll(@Param("comIds") List<Long> comIds);

    List<PostsComments> postsCommentsFindAll(@Param("comIds") List<Long> comIds);

}
