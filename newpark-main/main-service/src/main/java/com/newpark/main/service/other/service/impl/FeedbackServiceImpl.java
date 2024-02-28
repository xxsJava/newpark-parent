package com.newpark.main.service.other.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.Feedback;
import com.newpark.main.service.entity.Report;
import com.newpark.main.service.other.mapper.FeedbackMapper;
import com.newpark.main.service.other.service.IFeedbackService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 反馈表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public List<Feedback> feedbackFindByAll(PageInfoVo pageInfoVo) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());
        QueryWrapper<Feedback> queryWrap = new QueryWrapper<>();
        return PageInfo.of(feedbackMapper.selectList(queryWrap)).getList();
    }

    @Override
    public Boolean feedbackFindIns(Feedback feedback) {
        feedback.setFeedbackId(idGeneratorSnowflake.snowflakeId());
        feedback.setStatus("NO");
        return feedbackMapper.insert(feedback) > 0;
    }

    @Override
    public Boolean feedbackFindUpt(Long feedbackId, String status) {
        UpdateWrapper<Feedback> updateWrap = new UpdateWrapper<>();
        updateWrap.eq("feedback_id",feedbackId);
        updateWrap.set("status",status);
        return feedbackMapper.update(null,updateWrap) > 0;
    }

}
