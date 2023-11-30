package com.newpark.main.service.reward.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.RewardApp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/rewardAppApi")
    @ApiOperation(notes = "悬赏申请API", value = "悬赏申请API")
    public R<?> rewardAppIns(RewardApp rewardApp){
        return R.ok(rewardApp);
    }

}
