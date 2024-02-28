package com.newpark.main.service.water.service.impl;

import com.newpark.main.service.water.entity.Feedback;
import com.newpark.main.service.water.mapper.FeedbackMapper;
import com.newpark.main.service.water.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 反馈表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-12-28
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
