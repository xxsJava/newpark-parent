package com.newpark.main.service.reward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.RewardComTask;
import com.newpark.main.service.reward.mapper.RewardComTaskMapper;
import com.newpark.main.service.reward.service.IRewardComTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 悬赏完成表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@Service
public class RewardComTaskServiceImpl extends ServiceImpl<RewardComTaskMapper, RewardComTask> implements IRewardComTaskService {

    @Resource
    private RewardComTaskMapper rewardComTaskMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public Boolean rewardComTaskIns(RewardComTask rewardComTask) {
        rewardComTask.setRComId(idGeneratorSnowflake.snowflakeId());

        return rewardComTaskMapper.insert(rewardComTask) >= 1;
    }
}
