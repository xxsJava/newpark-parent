package com.newpark.main.service.reward.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Reward;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 悬赏表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
public interface IRewardService extends IService<Reward> {

    R<?> rewardFindAll(PageInfoVo pageInfoVo);

    R<?> rewardFindIns(Reward reward);

    R<?> rewardPubUpt(Reward reward);

    R<?> rewardPubDel(Long raId);
}
