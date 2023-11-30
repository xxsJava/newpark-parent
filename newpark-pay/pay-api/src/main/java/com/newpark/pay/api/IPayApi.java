package com.newpark.pay.api;

import com.newpark.pay.api.fallback.PayFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xxs18
 * @Description 支付api
 * @Date 2023/11/9 16:29
 **/
@FeignClient(name = "newpark-pay" , fallback = PayFallback.class)
public interface IPayApi {

    @GetMapping("/pay/testPay")
    public String payTest();

}
