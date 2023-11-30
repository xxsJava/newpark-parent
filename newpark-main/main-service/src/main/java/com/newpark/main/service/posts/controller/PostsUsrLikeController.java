package com.newpark.main.service.posts.controller;

import com.newpark.base.model.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/postsUsrLikeApi")
    @ApiOperation(notes = "添加点赞信息API", value = "添加点赞信息API")
    public R<?> postsUsrLikeIns(){
        return R.ok();
    }

    @PutMapping("/postsUsrLikeApi")
    @ApiOperation(notes = "修改点赞信息API", value = "修改点赞信息API")
    public R<?> postsUsrLikeUpt(){
        return R.ok();
    }
}
