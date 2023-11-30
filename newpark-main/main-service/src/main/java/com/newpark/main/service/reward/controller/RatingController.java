package com.newpark.main.service.reward.controller;


import com.newpark.base.model.vo.R;
import com.newpark.main.service.entity.Rating;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评价表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/rating")
@Api(value = "rating", tags = "悬赏评价功能")
public class RatingController {

    @PostMapping("/ratingApi")
    @ApiOperation(notes = "发布发布API", value = "发布评价API")
    public R<?> ratingIns(Rating rating){
        return R.ok();
    }

}
