package com.newpark.sso.service.impl;

import com.newpark.sso.entity.Rating;
import com.newpark.sso.mapper.RatingMapper;
import com.newpark.sso.service.IRatingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
