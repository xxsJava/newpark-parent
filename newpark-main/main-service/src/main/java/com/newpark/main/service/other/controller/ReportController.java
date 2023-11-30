package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Report;
import com.newpark.pojo.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 举报表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/report")
@Api(value = "report", tags = "举报功能")
public class ReportController {

    @GetMapping("/reportApi")
    @ApiOperation(notes = "举报浏览API", value = "举报浏览API")
    public R<?> reportFindByAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(pageInfoVo);
    }

    @PostMapping("/reportApi")
    @ApiOperation(notes = "添加举报API", value = "添加举报API")
    public R<?> reportIns(Report report){
        return R.ok(report);
    }

}
