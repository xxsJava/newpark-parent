package com.newpark.main.service.posts.controller;

import com.alibaba.fastjson.JSON;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.main.service.entity.PostsCollection;
import com.newpark.main.service.posts.service.IPostsCollectionService;
import com.newpark.pojo.dto.SysUsrDto;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-23
 */
@Slf4j
@RestController
@RequestMapping("/posts-collection")
@Api(value = "collection", tags = "收藏管理")
public class PostsCollectionController {

    @Resource
    private IPostsCollectionService iPostsCollectionService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/collectionApi")
    @ApiOperation(notes = "浏览收藏API", value = "浏览收藏API")
    public R<?> collectionFindAll(@Validated PageInfoVo pageInfoVo, HttpServletRequest request) {
        // 获取用户token
        String token = request.getHeader("Content-TOKEN");
        try {
            long uId = Long.parseLong(JwtUtil.getToken(redisUtils.get(token)).getClaim("uId").asString());
            return R.ok(iPostsCollectionService.collectionFindAll(pageInfoVo, uId));
        } catch (Exception e) {
            log.error("jwt错误---->", e);
            return R.failed(ResponseCodeEnum.USER_TOKEN_PARAMETER_ERROR);
        }
    }

    @PostMapping("/collectionApi")
    @ApiOperation(notes = "添加收藏API", value = "添加收藏API")
    public R<?> collectionFindIns(@RequestBody @Validated PostsCollection postsCollection, HttpServletRequest request) {
        // 获取用户token
        String token = request.getHeader("Content-TOKEN");

        try {
            postsCollection.setUId(Long.parseLong(JwtUtil.getToken(redisUtils.get(token)).getClaim("uId").asString()));
        } catch (UnsupportedEncodingException e) {
            log.error("jwt错误---->", e);
            return R.failed(ResponseCodeEnum.USER_TOKEN_PARAMETER_ERROR);
        }

        return R.ok(iPostsCollectionService.collectionFindIns(postsCollection));
    }

    @DeleteMapping("/collectionApi/{cId}")
    @ApiOperation(notes = "删除收藏API", value = "删除收藏API")
    public R<?> collectionDel(@PathVariable("cId") Long cId) {
        return R.ok(iPostsCollectionService.collectionDel(cId));
    }

}
