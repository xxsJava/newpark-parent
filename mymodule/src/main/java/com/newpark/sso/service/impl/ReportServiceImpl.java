package com.newpark.sso.service.impl;

import com.newpark.sso.entity.Report;
import com.newpark.sso.mapper.ReportMapper;
import com.newpark.sso.service.IReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 举报表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

}
