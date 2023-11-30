package com.newpark.main.service.posts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newpark.main.service.entity.PostsLabel;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 帖子标签 Mapper 接口
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Mapper
public interface PostsLabelMapper extends BaseMapper<PostsLabel> {

}
