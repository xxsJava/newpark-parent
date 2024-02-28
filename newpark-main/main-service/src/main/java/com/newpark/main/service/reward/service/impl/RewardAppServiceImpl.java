package com.newpark.main.service.reward.service.impl;

import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.RewardApp;
import com.newpark.main.service.reward.mapper.RewardAppMapper;
import com.newpark.main.service.reward.service.IRewardAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 接受悬赏表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@Service
public class RewardAppServiceImpl extends ServiceImpl<RewardAppMapper, RewardApp> implements IRewardAppService {

    @Resource
    private RewardAppMapper rewardAppMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public Boolean rewardAppIns(RewardApp rewardApp) {
        rewardApp.setAppId(idGeneratorSnowflake.snowflakeId());
        return rewardAppMapper.insert(rewardApp) >= 1;
    }
}
