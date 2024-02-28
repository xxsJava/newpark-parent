package com.newpark.main.service.posts.controller;

import com.newpark.base.model.vo.R;
import com.newpark.core.utils.LoginVerify;
import com.newpark.main.service.entity.PostsUsrLike;
import com.newpark.main.service.entity.vo.PostsUsrLikeVo;
import com.newpark.main.service.posts.service.IPostsUsrLikeService;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/posts-usr-like")
@Api(value = "posts-usr-like", tags = "帖子点赞管理")
public class PostsUsrLikeController {

    @Resource
    private IPostsUsrLikeService iPostsUsrLikeService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/postsUsrLikeApi")
    @ApiOperation(notes = "浏览点赞信息API", value = "浏览点赞信息API")
    public R<?> postsUsrLikeFindAll(PageInfoVo pageInfoVo){

        return R.ok();
    }

    @PostMapping("/postsUsrLikeApi")
    @ApiOperation(notes = "添加点赞信息API", value = "添加点赞信息API")
    public R<?> postsUsrLikeIns(@RequestBody @Validated PostsUsrLike postsUsrLike){
        postsUsrLike.setUId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(iPostsUsrLikeService.postsUsrLikeIns(postsUsrLike));
    }

    @PutMapping("/postsUsrLikeApi")
    @ApiOperation(notes = "逻辑删除点赞信息API", value = "逻辑删除修改点赞信息API")
    public R<?> postsUsrLikeUpt(@RequestBody @Validated PostsUsrLikeVo postsUsrLikeVo){
        return R.ok(iPostsUsrLikeService.postsUsrLikeUpt(postsUsrLikeVo));
    }

    @PutMapping("/postsUsrLikeApi/{likeId}")
    @ApiOperation(notes = "删除点赞信息API", value = "删除点赞信息API")
    public R<?> postsUsrLikeDel(@PathVariable("likeId") Long likeId){
        return R.ok(iPostsUsrLikeService.postsUsrLikeDel(likeId));
    }

}
