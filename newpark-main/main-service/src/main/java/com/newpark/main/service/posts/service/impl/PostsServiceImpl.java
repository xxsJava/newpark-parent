package com.newpark.main.service.posts.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yulichang.query.MPJQueryWrapper;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.entity.Posts;
import com.newpark.main.service.entity.PostsComments;
import com.newpark.main.service.entity.PostsLabel;
import com.newpark.main.service.entity.dto.PostsCommentsDto;
import com.newpark.main.service.entity.dto.PostsDto;
import com.newpark.main.service.entity.vo.PostParamVo;
import com.newpark.main.service.entity.vo.PostReviewParamVo;
import com.newpark.main.service.entity.vo.PostUptVo;
import com.newpark.main.service.posts.mapper.PostsCommentsMapper;
import com.newpark.main.service.posts.mapper.PostsLabelDerMapper;
import com.newpark.main.service.posts.mapper.PostsLabelMapper;
import com.newpark.main.service.posts.mapper.PostsMapper;
import com.newpark.main.service.posts.service.IPostsService;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;

/**
 * <p>
 * 帖子表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Service
public class PostsServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostsService {

    @Resource
    private PostsMapper postsMapper;

    @Resource
    private PostsCommentsMapper postsCommentsMapper;

    @Resource
    private PostsLabelMapper postsLabelMapper;

    @Resource
    private RedisUtils redisUtils;


    @Resource
    private PostsLabelDerMapper postsLabelDerMapper;

    /**
     * 1.id 没参数时查全国学校的内容 2.id 带参数时查指定学校的内容
     *
     * @param pageInfo 分页和id
     * @param postParamVo 条件过滤参数
     * @return
     */
    @Override
    public R<?> getPostsFindAll(PageInfoVo pageInfo, PostParamVo postParamVo) {
        PageHelper.startPage(pageInfo.getPageNo(), pageInfo.getPageSize());

        StringBuilder strKey = new StringBuilder().append(pageInfo.getPageNo()).append("-").append(pageInfo.getPageSize())
            .append("-").append(postParamVo.getSchoolId());

        String postsRedis = redisUtils.get(strKey.toString());

        // 从redis里获取帖子信息
        if (StringUtils.isNotEmpty(postsRedis)) {
            List<PostsDto> postsList = JSONArray.parseArray(postsRedis, PostsDto.class);
            return R.ok(postsList);
        }

        // 获取到帖子
        List<PostsDto> postsList = postsMapper.getPostsFindALL(postParamVo);

        if (postsList.isEmpty()) {
            return R.ok();
        }

        //redis 获取标签
        List<PostsLabel> postsLabelList = JSONArray.parseArray(redisUtils.get("postsComLab"), PostsLabel.class);

        if(postsLabelList == null) {
            // 获取到标签
            MPJQueryWrapper<PostsLabel> postsLabs = new MPJQueryWrapper<>();
            postsLabs.select("t1.posts_id,t.lab_id as lableId,t.lab_text as lableText")
                    .rightJoin("posts_label_der t1 ON t.lab_id = t1.lab_id")
                    .in("t1.posts_id", postsList.stream().map(PostsDto::getTId).collect(Collectors.toList()));
            postsLabelList = postsLabelMapper.selectList(postsLabs);
            //标签存redis 7天失效避免数据未更新
            redisUtils.set("postsComLab", postsLabelList, PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        }

        // 标签分类
        Map<Long, List<PostsLabel>> subCommentsMap =
                postsLabelList.stream().collect(Collectors.groupingBy(PostsLabel::getPostsId));

        List<PostsDto> result = postsList.stream()
            .peek(posts -> posts.setLabs(subCommentsMap.getOrDefault(posts.getTId(), Collections.emptyList())))
            .collect(Collectors.toList());

        // 存入redis 进行缓存30分钟更新一次
        redisUtils.set(strKey.toString(), result, PrefixMsgEnum.REDIS_TIME_DAY_60.getTime() * 30);

        return R.ok(PageInfo.of(result).getList());
    }

    /**
     * 1. 指定帖子评论和回复
     * @param pageInfoVo
     * @return
     */
    @Override
    public R<?> postsReviewParentFindAll(PageInfoVo pageInfoVo, PostReviewParamVo postReviewParamVo) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());
        // 获取父元素
        List<PostsCommentsDto> commentsList = postsMapper.postsReviewParentFindALL(postReviewParamVo.getPostsId());

        if (commentsList.isEmpty()) {
            return R.ok();
        }

        // 获取子元素
        QueryWrapper<PostsComments> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("com_parent_id",
            commentsList.stream().map(PostsCommentsDto::getComId).collect(Collectors.toList()));
        List<PostsComments> subComments = postsCommentsMapper.selectList(queryWrapper);

        Map<Long, List<PostsComments>> subCommentsMap =
            subComments.stream().collect(Collectors.groupingBy(PostsComments::getComParentId));
        // 评论回复拼接
        List<PostsCommentsDto> result = commentsList.stream().peek(comments -> {
            comments.setComs(subCommentsMap.getOrDefault(comments.getComId(), Collections.emptyList()));
        }).collect(Collectors.toList());

        return R.ok(PageInfo.of(result).getList());
    }

    /**
     * 1. 编辑作者发布帖子
     * @param posts
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<?> postsUpt(PostUptVo posts) {
        UpdateWrapper<Posts> postsUpt = new UpdateWrapper<>();
        postsUpt.eq("t_id", posts.getTId()).set(StringUtils.isNotBlank(posts.getTTitle()), "t_title", posts.getTTitle())
            .set(StringUtils.isNotBlank(posts.getTContext()), "t_context", posts.getTContext())
            .set("t_last_time", posts.getTLastTime()).set("t_like_count", posts.getTLikeCount())
            .set("t_com_count", posts.getTComCount()).set("t_forward_count", posts.getTForwardCount())
            .set("t_browse_count", posts.getTBrowseCount());

        return R.ok(postsMapper.update(null, postsUpt));
    }

    /**
     * 1. 删除作者发布的帖子内容
     * @param tId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<?> postsDel(Long tId) {

        PostParamVo posts = new PostParamVo();
        posts.setPostsId(tId);
        PostsDto comments = postsMapper.getPostsFindALL(posts).get(0);

        Map<String, Object> delMap = new HashMap<>();
        delMap.put("posts_id", comments.getTId());
        // 删除帖子标签
        postsLabelDerMapper.deleteByMap(delMap);
        // 删除评论
        postsCommentsMapper.deleteByMap(delMap);
        // 删除帖子
        postsMapper.deleteById(comments.getTId());
        // 删除成功
        return R.failed(ResponseCodeEnum.NO_CONTENT);
    }

}
