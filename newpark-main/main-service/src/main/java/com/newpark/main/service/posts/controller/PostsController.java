package com.newpark.main.service.posts.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import com.alibaba.fastjson.JSONArray;
import com.newpark.base.constant.Constant;
import com.newpark.core.utils.DateUtils;
import com.newpark.core.utils.LoginVerify;
import com.newpark.core.utils.SnowflakeIdWorker;
import com.newpark.im.api.IIMApi;
import com.newpark.main.service.entity.vo.PostsIsParamVo;
import com.newpark.pojo.SysRouts;
import com.newpark.pojo.UsersIm;
import com.newpark.redis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

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
    private RedisUtils redisUtils;

    @Resource
    private IPostsLabelDerService iPostsLabelDerService;

    @Resource
    private IIMApi imApi;

    @GetMapping("/postsApi")
    @ApiOperation(notes = "帖子查询全校|本校API", value = "帖子查询全校|本校API")
    public R<?> postsFindAll(@Validated PageInfoVo pageInfo, @Validated PostParamVo postParamVo) {
        return R.ok(iPostsService.postsFindAll(pageInfo, postParamVo));
    }

    @GetMapping("/postsOneApi")
    @ApiOperation(notes = "帖子条件查询单条API", value = "帖子条件查询单条API")
    public R<?> postsFindOne(@Validated PostsIsParamVo postParamVo) {
        return iPostsService.postsFIndIsByAll(postParamVo);
    }

    @PostMapping("/postsApi")
    @ApiOperation(notes = "帖子发布API", value = "帖子发布API")
    public R<?> postsIns(@RequestBody @Validated PostsInsVo postsInsVo) {
        postsInsVo.setTAuthorId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(iPostsLabelDerService.postsIns(postsInsVo));
    }

    @PutMapping("/postsApi")
    @ApiOperation(notes = "帖子编辑API", value = "帖子编辑API")
    public R<?> postsUpt(@RequestBody @Validated PostUptVo posts) {
        return R.ok(iPostsService.postsUpt(posts));
    }

    @DeleteMapping("/postsApi/{tId}")
    @ApiOperation(notes = "帖子删除API", value = "帖子删除API")
    public R<?> postsDel(@PathVariable("tId") @Positive(message = ValidatedStrMsg.ERROR_MSG) @Min(value = 1000000L,
        message = ValidatedStrMsg.RANGE_MSG) Long tId) {
        return R.ok(iPostsService.postsDel(tId));
    }

    @PostMapping("/userRegisterIM")
    public R<?> userRegisterIM(@RequestBody List<UsersIm> usersIms){
        return R.ok(imApi.userRegister(usersIms));
    }
}
