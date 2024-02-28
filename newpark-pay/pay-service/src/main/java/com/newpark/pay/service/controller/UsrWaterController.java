package com.newpark.pay.service.controller;


import cn.hutool.crypto.digest.MD5;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.newpark.base.constant.Constant;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.pay.service.entity.vo.WaterParamsVo;
import com.newpark.pay.service.service.IUsrWaterService;
import com.newpark.pojo.vo.PageInfoVo;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 支付流水表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-12-18
 */
@RestController
@RequestMapping("/usr-water")
@Api(value = "usr-water", tags = "钱包功能")
public class UsrWaterController {

    @Resource
    private IUsrWaterService iUsrWaterService;

    @Resource
    private RedisUtils redisUtils;


    @GetMapping("/waterApi")
    @ApiOperation(notes = "钱包流水账单查询 API", value = "钱包流水账单查询 API")
    public R<?> usrWaterFind(@Validated PageInfoVo pageInfoVo, WaterParamsVo waterParamsVo, HttpServletRequest request){

        String token = request.getHeader(Constant.Token.REQUEST_HEADER);

        try {
            String uId =  JwtUtil.getToken(redisUtils.get(token)).getClaim("uId").asString();
            return R.ok(iUsrWaterService.usrWaterFindAll(pageInfoVo,waterParamsVo,uId));
        }catch (Exception e) {
            return R.failed(ResponseCodeEnum.UNAUTHORIZED);
        }
    }
}
