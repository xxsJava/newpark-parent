package com.newpark.service;

import com.newpark.service.impl.SmsHWYServiceImpl;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/28 14:12
 **/
public interface ISmsHWYService {

    static SmsHWYServiceImpl create() {
        return new SmsHWYServiceImpl();
    }

    void sendSms(String phone,String code);

}
