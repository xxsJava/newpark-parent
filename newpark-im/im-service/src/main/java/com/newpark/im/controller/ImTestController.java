package com.newpark.im.controller;

import javax.annotation.Resource;

import com.newpark.core.utils.LoginVerify;
import com.newpark.redis.utils.RedisUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.newpark.im.service.IImCoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/23 9:48
 **/
@RestController
@RequestMapping("/v1")
@Api(value = "IM", tags = "即时通讯", description = "即时通讯")
public class ImTestController {

    @Resource
    private IImCoreService iImCoreService;
    @Resource
    private RedisUtils redisUtils;

    @PostMapping("/getClientConfig")
    @ApiOperation(notes = "IM用户验证", value = "IM用户验证")
    public JSON getClientConfig(){
        return iImCoreService.getClientConfig(LoginVerify.create().getUsrJWTData("uId",redisUtils));
    }

    @PostMapping("/groupGetGroupsInfo")
    @ApiOperation(notes = "IM获取群组信息", value = "IM获取群组信息")
    public JSON groupGetGroupsInfo(@RequestBody List<String> groupIds){
        return iImCoreService.groupGetGroupsInfo(groupIds);
    }
}
