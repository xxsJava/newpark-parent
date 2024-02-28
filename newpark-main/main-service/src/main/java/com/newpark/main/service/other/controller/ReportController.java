package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.core.utils.LoginVerify;
import com.newpark.main.service.entity.Report;
import com.newpark.main.service.other.service.IReportService;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @Resource
    private IReportService iReportService;

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/reportApi")
    @ApiOperation(notes = "举报浏览API", value = "举报浏览API")
    public R<?> reportFindByAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(iReportService.reportFindByAll(pageInfoVo));
    }

    @PostMapping("/reportApi")
    @ApiOperation(notes = "添加举报API", value = "添加举报API")
    public R<?> reportIns(@RequestBody @Validated Report report){
        report.setUId(Long.parseLong(LoginVerify.create().getUsrJWTData("uId",redisUtils)));
        return R.ok(iReportService.reportIns(report));
    }

    @PutMapping("/reportApi/{rId}/{status}")
    @ApiOperation(notes = "修改举报API", value = "修改举报API")
    public R<?> reportUpt(@PathVariable("rId") Long rId,@PathVariable("status") String status){
        return R.ok(iReportService.reportUpt(rId,status));
    }

}
