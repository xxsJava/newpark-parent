package com.newpark.pay.api.fallback;

import com.newpark.pay.api.IPayApi;
import org.springframework.stereotype.Component;

/**
 * @Author xxs18
 * @Description pay容错
 * @Date 2023/11/9 16:30
 **/
@Component
public class PayFallback implements IPayApi {
    @Override
    public String payTest() {
        return "乌拉";
    }
}
