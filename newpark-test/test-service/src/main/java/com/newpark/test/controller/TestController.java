package com.newpark.test.controller;

import com.newpark.base.model.vo.R;
import com.newpark.test.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/8 10:22
 **/
@Slf4j
@RestController
public class TestController {

    @Resource
    private ISysUserService iSysUserService;

    @GetMapping("/test")
    public R getTest(){
        log.info("接口调试");
        return R.ok("1","ok");
    }

    @GetMapping("/findAll")
    public R getDataFindAll(){
        log.info("数据接口调试");
        return R.ok(iSysUserService.getSysUserFindAll());
    }

}
