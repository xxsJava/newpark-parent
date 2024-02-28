package com.newpark.main.service.posts.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.entity.dto.PostsCommentsDto;
import com.newpark.main.service.entity.dto.PostsDto;
import com.newpark.main.service.entity.vo.PostParamVo;
import com.newpark.main.service.entity.vo.PostsInsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * 帖子表 Mapper 接口
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Mapper
public interface PostsMapper extends BaseMapper<Posts> {

    /**
     * 帖子查询操作
     * @param postParamVo
     * @return
     */
    List<PostsDto> getPostsFindALL(PostParamVo postParamVo);

    @Select("SELECT t1.lab_id,lab_text FROM posts_label_der t1 LEFT JOIN posts_label t2 ON t1.lab_id = t2.lab_id WHERE t1.posts_id = #{tId}")
    List<PostsLabel> getPostLabelFindALL(@Param("tId") Long tId);

    /**
     * 查询父级评论
     * @param postsId
     * @return
     */
    @Select("SELECT com_id,posts_id,com_content,u_path,u_nikname,start_time,com_support,com_parent_id,u_id FROM posts_comments t LEFT JOIN usr_info t1 ON t.u_id = t1.info_id WHERE com_parent_id = 0 AND posts_id = #{postsId}")
    List<PostsCommentsDto> postsReviewParentFindALL(@Param("postsId") Long postsId);

    @Insert("INSERT INTO `posts` (`t_id`, `t_title`, `t_context`, `t_pub_time`, `t_last_time`, `t_author_id`, `t_type_id`, `school_id`) VALUES (#{tId}, #{tTitle}, #{tContext}, #{tPubTime}, #{tLastTime}, #{tAuthorId},  #{tTypeId},  #{schoolId})")
    Integer postsIns(PostsInsVo postsInsVo);
}
