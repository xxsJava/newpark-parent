package com.newpark.service.impl;

import com.newpark.config.SmsConfig;
import com.newpark.service.ISmsHWYService;
import com.newpark.utils.HwySmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/28 14:13
 **/
@Slf4j
@Service
public class SmsHWYServiceImpl implements ISmsHWYService {

    @Override
    public void sendSms(String phone, String code) {
        SmsConfig smsConfig = new SmsConfig();
        smsConfig.setReceiver(phone);
        smsConfig.setTemplateParas("["+code+"]");
        try {
            // 发送短信
            HwySmsUtils.create().sendSms(smsConfig);
        } catch (Exception e) {
            log.error("短信发送失败");
        }
    }
}
