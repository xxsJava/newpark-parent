package com.newpark.sso.service.impl;

import com.newpark.sso.entity.Feedback;
import com.newpark.sso.mapper.FeedbackMapper;
import com.newpark.sso.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
