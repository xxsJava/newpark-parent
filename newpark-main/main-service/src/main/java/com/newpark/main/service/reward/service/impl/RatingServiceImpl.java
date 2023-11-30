package com.newpark.main.service.reward.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.Rating;
import com.newpark.main.service.reward.mapper.RatingMapper;
import com.newpark.main.service.reward.service.IRatingService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评价表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@Service
public class RatingServiceImpl extends ServiceImpl<RatingMapper, Rating> implements IRatingService {

}
