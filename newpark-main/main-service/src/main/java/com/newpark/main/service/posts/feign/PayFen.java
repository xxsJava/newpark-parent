package com.newpark.main.service.posts.feign;

import com.newpark.pay.api.IPayApi;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/15 10:11
 **/
public class PayFen implements IPayApi {

    @Override
    public String payTest() {
        return "嘎嘎嘎";
    }
}
