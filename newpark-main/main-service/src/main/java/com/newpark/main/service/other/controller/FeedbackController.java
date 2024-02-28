package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.core.utils.LoginVerify;
import com.newpark.main.service.entity.Feedback;
import com.newpark.main.service.other.service.IFeedbackService;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 反馈表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/feedback")
@Api(value = "feedback", tags = "反馈功能")
public class FeedbackController {

    @Resource
    private IFeedbackService feedbackService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/feedbackApi")
    @ApiOperation(notes = "反馈浏览API", value = "反馈浏览API")
    public R<?> feedbackFindByAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(feedbackService.feedbackFindByAll(pageInfoVo));
    }

    @PostMapping("/feedbackApi")
    @ApiOperation(notes = "添加反馈API", value = "添加反馈API")
    public R<?> feedbackFindIns(@RequestBody @Validated Feedback feedback){
        feedback.setUId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(feedbackService.feedbackFindIns(feedback));
    }

    @PutMapping("/feedbackApi/{feedbackId}/{status}")
    @ApiOperation(notes = "修改反馈状态API", value = "修改反馈状态API")
    public R<?> feedbackFindUpt(@PathVariable("feedbackId") Long feedbackId,@PathVariable("status") String status){
        return R.ok(feedbackService.feedbackFindUpt(feedbackId,status));
    }
}
