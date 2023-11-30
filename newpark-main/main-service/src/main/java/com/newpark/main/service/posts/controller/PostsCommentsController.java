package com.newpark.main.service.posts.controller;


import com.newpark.main.service.entity.vo.PostReviewParamVo;
import com.newpark.main.service.posts.service.IPostsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.PostsComments;
import com.newpark.pojo.vo.PageInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/postsComments")
@Api(value = "postsComments", tags = "评论管理", description = "评论操作")
public class PostsCommentsController {

    @Resource
    private IPostsService iPostsService;

    @GetMapping("/postsCommentsApi")
    @ApiOperation(notes = "帖子评论查询API", value = "帖子评论查询API")
    public R<?> postsReviewFindAll(@Validated PageInfoVo pageInfo, @Validated PostReviewParamVo postReviewParamVo) {
        return iPostsService.postsReviewParentFindAll(pageInfo, postReviewParamVo);
    }
    @PostMapping("/postsCommentsApi")
    @ApiOperation(notes = "发布评论API", value = "发布评论API")
    public R setPostsComIns(@RequestBody PostsComments postsComs){

        return R.ok(postsComs);
    }

    @DeleteMapping("/postsCommentsApi/{comId}")
    @ApiOperation(notes = "删除评论API", value = "删除评论API")
    public R setPostsComDel(@PathVariable("comId") Long comId){

        return R.ok(comId);
    }

    @PutMapping("/postsCommentsApi")
    @ApiOperation(notes = "编辑评论API", value = "编辑评论API")
    public R setPostsComPut(@RequestBody PostsComments postsComments){

        return R.ok(postsComments);
    }
}
