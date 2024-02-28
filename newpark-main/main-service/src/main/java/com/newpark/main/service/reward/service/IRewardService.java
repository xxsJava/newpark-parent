package com.newpark.main.service.reward.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Reward;
import com.newpark.main.service.entity.vo.RewardParamVo;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 悬赏表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
public interface IRewardService extends IService<Reward> {

    List<Reward> rewardFindAll(PageInfoVo pageInfoVo);

    Boolean rewardFindIns(Reward reward);

    Boolean rewardPubUpt(Reward reward);

    Boolean rewardPubDel(Long raId);

    List<Reward> rewardFindOne(RewardParamVo rewardParamVo);
}
