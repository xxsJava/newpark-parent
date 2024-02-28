package com.newpark.main.service.other.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.main.service.entity.Report;
import com.newpark.pojo.vo.PageInfoVo;

import java.util.List;

/**
 * <p>
 * 举报表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
public interface IReportService extends IService<Report> {

    List<Report> reportFindByAll(PageInfoVo pageInfoVo);

    Boolean reportIns(Report report);

    Boolean reportUpt(Long rId,String status);
}
