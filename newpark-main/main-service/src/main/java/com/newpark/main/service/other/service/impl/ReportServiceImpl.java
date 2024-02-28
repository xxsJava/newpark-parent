package com.newpark.main.service.other.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.core.utils.StringUtils;
import com.newpark.main.service.config.IdGeneratorSnowflake;
import com.newpark.main.service.entity.Report;
import com.newpark.main.service.other.mapper.ReportMapper;
import com.newpark.main.service.other.service.IReportService;
import com.newpark.pojo.vo.PageInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 举报表 服务实现类
 * </p>
 * @author xxs18
 * @since 2023-11-28
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public List<Report> reportFindByAll(PageInfoVo pageInfoVo) {
        PageHelper.startPage(pageInfoVo.getPageNo(), pageInfoVo.getPageSize());
        QueryWrapper<Report> queryWrap = new QueryWrapper<>();

        return PageInfo.of(reportMapper.selectList(queryWrap)).getList();
    }

    @Override
    public Boolean reportIns(Report report) {
        report.setReportId(idGeneratorSnowflake.snowflakeId());
        report.setStatus("NO");
        return reportMapper.insert(report) > 0;
    }

    @Override
    public Boolean reportUpt(Long rId,String status) {
        UpdateWrapper<Report> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("report_id",rId);
        queryWrapper.set(StringUtils.isNotEmpty(status),"status",status);

        return reportMapper.update(null,queryWrapper) >0;
    }
}
