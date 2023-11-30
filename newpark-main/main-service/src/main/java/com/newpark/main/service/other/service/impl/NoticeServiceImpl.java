package com.newpark.main.service.other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.Notice;
import com.newpark.main.service.other.mapper.NoticeMapper;
import com.newpark.main.service.other.service.INoticeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}
