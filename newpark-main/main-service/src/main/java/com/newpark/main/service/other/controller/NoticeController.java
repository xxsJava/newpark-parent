package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Notice;
import com.newpark.pojo.vo.PageInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知表 前端控制器
 * </p>
 * @author xxs18
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/notice")
@Api(value = "notice", tags = "通知功能")
public class NoticeController {

    @PostMapping("/noticeApi")
    @ApiOperation(notes = "通知发布API", value = "通知发布API")
    public R<?> feedbackFindIns(Notice notice){
        return R.ok(notice);
    }

    @GetMapping("/noticeApi")
    @ApiOperation(notes = "通知回调API", value = "通知回调API")
    public R<?> feedbackFindByAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(pageInfoVo);
    }

}
