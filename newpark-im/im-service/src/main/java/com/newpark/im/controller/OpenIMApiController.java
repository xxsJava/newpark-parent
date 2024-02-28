package com.newpark.im.controller;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSON;
import com.newpark.core.utils.LoginVerify;
import com.newpark.im.service.IImCoreService;
import com.newpark.pojo.UsersIm;
import com.newpark.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xxs18
 * @Description
 * @Date 2024/1/2 16:11
 **/
@Slf4j
@RestController
@RequestMapping("/OpenIM")
@Api(value = "IM", tags = "即时通讯API", description = "即时通讯API")
public class OpenIMApiController {

    @Resource
    private IImCoreService imCoreService;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 1. 用户通过AppServer完成账号注册后，AppServer再调用该接口导入OpenIM，实现账号集成。
     * @param userIm
     * @return
     */
    @PostMapping("/user_register")
    @ApiOperation(notes = "IM用户注册", value = "IM用户注册")
    public JSON userRegister(@RequestBody List<UsersIm> userIm){
        log.info("OPEN-IM----------->{}",userIm);
        return imCoreService.userRegister(userIm);
    }


}
