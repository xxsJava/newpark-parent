package com.newpark.main.service.reward.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.Reward;
import com.newpark.main.service.reward.mapper.RewardMapper;
import com.newpark.main.service.reward.service.IRewardService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 悬赏表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@Service
public class RewardServiceImpl extends ServiceImpl<RewardMapper, Reward> implements IRewardService {

    @Resource
    private RewardMapper rewardMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 1. 展示未被接单的悬赏
     * @param pageInfoVo
     * @return
     */
    @Override
    public R<?> rewardFindAll(PageInfoVo pageInfoVo) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());

        QueryWrapper<Reward> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("r_id", "u_id", "start_time", "end_time", "r_title", "r_desc", "r_money", "r_imgs")
                .notInSql("r_id", "SELECT r_id FROM reward_app");
        List<Reward> resultList = rewardMapper.selectList(queryWrapper);

        return R.ok(PageInfo.of(resultList).getList());
    }

    /**
     * 1. 发布悬赏
     * @param reward
     * @return
     */
    @Override
    public R<?> rewardFindIns(Reward reward) {
        reward.setRId(idGeneratorSnowflake.snowflakeId());
        if(rewardMapper.insert(reward) >= 1){
            return R.failed(ResponseCodeEnum.CREATED);
        }
        return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 1. 发布者修改悬赏内容
     * @param reward
     * @return
     */
    @Override
    public R<?> rewardPubUpt(Reward reward) {
        UpdateWrapper<Reward> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("r_id",reward.getRId())
                .set(StringUtils.isNotBlank(reward.getRTitle()),"r_title",reward.getRTitle())
                .set(StringUtils.isNotBlank(reward.getRDesc()),"r_desc",reward.getRDesc())
                .set(StringUtils.isNotBlank(reward.getRImgs()),"r_imgs",reward.getRImgs());
        if(rewardMapper.update(null,updateWrapper)>=1){
            return R.failed(ResponseCodeEnum.CREATED);
        }
        return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
    }

    /**
     * 删除一个未被接单的悬赏
     * @param raId
     * @return
     */
    @Override
    public R<?> rewardPubDel(Long raId) {
        if(rewardMapper.deleteById(raId) >= 1){
            return R.failed(ResponseCodeEnum.NO_CONTENT);
        }
        return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
    }
}
