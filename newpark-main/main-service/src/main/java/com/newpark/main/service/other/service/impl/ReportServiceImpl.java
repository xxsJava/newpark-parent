package com.newpark.main.service.other.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.main.service.entity.Report;
import com.newpark.main.service.other.mapper.ReportMapper;
import com.newpark.main.service.other.service.IReportService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 举报表 服务实现类
 * </p>
 * @author xxs18
 * @since 2023-11-28
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

}
