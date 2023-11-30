package com.newpark.main.service.posts.controller;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.newpark.base.model.vo.R;
import com.newpark.base.vali.ValidatedStrMsg;
import com.newpark.main.service.entity.vo.PostParamVo;
import com.newpark.main.service.entity.vo.PostUptVo;
import com.newpark.main.service.entity.vo.PostsInsVo;
import com.newpark.main.service.posts.service.IPostsLabelDerService;
import com.newpark.main.service.posts.service.IPostsService;
import com.newpark.pojo.vo.PageInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 帖子表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Slf4j
@RestController
@RequestMapping("/posts")
@Api(value = "posts", tags = "帖子管理")
@Validated
public class PostsController {

    @Resource
    private IPostsService iPostsService;

    @Resource
    private IPostsLabelDerService iPostsLabelDerService;

    @GetMapping("/postsApi")
    @ApiOperation(notes = "帖子查询API", value = "帖子查询API")
    public R<?> postsFindAll(@Validated PageInfoVo pageInfo, @Validated PostParamVo postParamVo) {
        return iPostsService.getPostsFindAll(pageInfo, postParamVo);
    }

    @GetMapping("/postsOneApi")
    @ApiOperation(notes = "帖子条件查询API", value = "帖子条件查询API")
    public R<?> postsFindOne(@Validated PostParamVo postParamVo) {
        return R.ok(postParamVo);
    }

    @PostMapping("/postsApi")
    @ApiOperation(notes = "帖子发布API", value = "帖子发布API")
    public R<?> postsIns(@RequestBody @Validated PostsInsVo postsInsVo) {
        return iPostsLabelDerService.postsIns(postsInsVo);
    }

    @PutMapping("/postsApi")
    @ApiOperation(notes = "帖子编辑API", value = "帖子编辑API")
    public R<?> postsUpt(@RequestBody @Validated PostUptVo posts) {
        return iPostsService.postsUpt(posts);
    }

    @DeleteMapping("/postsApi/{tId}")
    @ApiOperation(notes = "帖子删除API", value = "帖子删除API")
    public R<?> postsDel(@PathVariable("tId") @Positive(message = ValidatedStrMsg.ERROR_MSG) @Min(value = 1000000L,
        message = ValidatedStrMsg.RANGE_MSG) Long tId) {
        return iPostsService.postsDel(tId);
    }

}
