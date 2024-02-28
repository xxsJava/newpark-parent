package com.newpark.sso.sys.controller;


import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.LoginVerify;
import com.newpark.core.utils.StringUtils;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Slf4j
@RestController
@RequestMapping("/usr")
@Api(value = "usr-info", tags = "用户信息管理", description = "用户信息管理")
public class SysUserController {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private ISysUserService iSysUserService;

    @PostMapping("/usrInfoFindById")
    @ApiOperation(notes = "获取用户API", value = "获取用户API")
    public R<?> usrInfoFindById(){
        String token = redisUtils.get(LoginVerify.create().getUsrToken());
        if(StringUtils.isEmpty(token)){
            return R.failed(ResponseCodeEnum.UNAUTHORIZED);
        }
        return R.ok(iSysUserService.usrInfoFindById(token));
    }

}
