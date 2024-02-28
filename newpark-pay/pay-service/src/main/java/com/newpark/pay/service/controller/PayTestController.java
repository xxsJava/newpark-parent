package com.newpark.pay.service.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newpark.base.model.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xxs18
 * @Description 支付测试
 * @Date 2023/11/9 14:20
 **/
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayTestController {

    @GetMapping("/testPay")
    public String payTest(){
        log.info("hello pay");
        return "hello pay";
    }


}
