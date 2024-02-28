package com.newpark.main.service.reward.controller;


import javax.annotation.Resource;

import com.newpark.core.utils.LoginVerify;
import com.newpark.redis.utils.RedisUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Reward;
import com.newpark.main.service.entity.vo.RewardParamVo;
import com.newpark.main.service.reward.service.IRewardService;
import com.newpark.pojo.vo.PageInfoVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 悬赏表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/reward")
@Api(value = "reward", tags = "悬赏功能")
public class RewardController {


    @Resource
    private IRewardService iRewardService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/rewardApi")
    @ApiOperation(notes = "悬赏浏览API", value = "悬赏浏览API")
    public R<?> rewardFindAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(iRewardService.rewardFindAll(pageInfoVo));
    }

    @GetMapping("/rewardOneApi")
    @ApiOperation(notes = "单条条件浏览悬赏API", value = "单条条件浏览悬赏API")
    public R<?> rewardFindOne(@RequestBody @Validated RewardParamVo rewardParamVo){
        return R.ok(iRewardService.rewardFindOne(rewardParamVo));
    }

    @PostMapping("/rewardApi")
    @ApiOperation(notes = "悬赏发布API", value = "悬赏发布API")
    public R<?> rewardPubIns(@RequestBody @Validated Reward reward){
        reward.setUId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(iRewardService.rewardFindIns(reward));
    }

    @PutMapping("/rewardApi")
    @ApiOperation(notes = "悬赏编辑API", value = "悬赏编辑API")
    public R<?> rewardPubUpt(@RequestBody @Validated Reward reward){
        return R.ok(iRewardService.rewardPubUpt(reward));
    }

    @DeleteMapping("/rewardApi/{raId}")
    @ApiOperation(notes = "悬赏删除API", value = "悬赏删除API")
    public R<?> rewardPubDel(@PathVariable("raId") Long raId){
        return R.ok(iRewardService.rewardPubDel(raId));
    }

}
