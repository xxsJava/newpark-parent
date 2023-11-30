package com.newpark.sso.service.impl;

import com.newpark.sso.entity.Notice;
import com.newpark.sso.mapper.NoticeMapper;
import com.newpark.sso.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
