package com.newpark.main.service.reward.controller;


import com.newpark.base.model.vo.R;
import com.newpark.core.utils.LoginVerify;
import com.newpark.main.service.entity.RewardApp;
import com.newpark.main.service.reward.service.IRewardAppService;
import com.newpark.main.service.reward.service.IRewardService;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 接受悬赏表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/reward-app")
@Api(value = "reward-app", tags = "悬赏接单功能")
public class RewardAppController {

    @Resource
    private IRewardAppService iRewardAppService;

    @Resource
    private RedisUtils redisUtils;

    @PostMapping("/rewardAppApi")
    @ApiOperation(notes = "悬赏申请API", value = "悬赏申请API")
    public R<?> rewardAppIns(@RequestBody @Validated RewardApp rewardApp){
        rewardApp.setTaskId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(iRewardAppService.rewardAppIns(rewardApp));
    }

}
