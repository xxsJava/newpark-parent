package com.newpark.main.service.other.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Feedback;
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
 * 反馈表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/feedback")
@Api(value = "feedback", tags = "反馈功能")
public class FeedbackController {

    @GetMapping("/feedbackApi")
    @ApiOperation(notes = "反馈浏览API", value = "反馈浏览API")
    public R<?> feedbackFindByAll(@Validated PageInfoVo pageInfoVo){
        return R.ok(pageInfoVo);
    }

    @PostMapping("/feedbackApi")
    @ApiOperation(notes = "添加反馈API", value = "添加反馈API")
    public R<?> feedbackFindIns(Feedback feedback){
        return R.ok(feedback);
    }

}
