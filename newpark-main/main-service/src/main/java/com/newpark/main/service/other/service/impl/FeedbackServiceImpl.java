package com.newpark.main.service.other.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.Feedback;
import com.newpark.main.service.other.mapper.FeedbackMapper;
import com.newpark.main.service.other.service.IFeedbackService;
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
