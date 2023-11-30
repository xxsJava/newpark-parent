package com.newpark.main.service.reward.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.RewardComTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 悬赏完成表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/reward-com-task")
@Api(value = "reward-com-task", tags = "悬赏完成功能")
public class RewardComTaskController {

    @PostMapping("/rewardComTaskApi")
    @ApiOperation(notes = "悬赏完成API", value = "悬赏完成API")
    public R<?> rewardComTaskIns(RewardComTask rewardComTask){
        return R.ok(rewardComTask);
    }

}
