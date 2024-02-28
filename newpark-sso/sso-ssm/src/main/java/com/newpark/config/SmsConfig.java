package com.newpark.config;

/**
 * @Author xxs18
 * @Description 短信配置
 * @Date 2023/12/28 13:59
 **/
public class SmsConfig {

    public final static String SMS_URL = "https://smsapi.cn-north-4.myhuaweicloud.com:443/sms/batchSendSms/v1";

    public final static String APP_KEY = "1O8Gc12M92j4bAoCo3TgqY29FLMl";

    public final static String APP_SECRET = "2o0sggu083k5UvmGtYoG7vF5wsve"; // APP_Secret

    public final static String SENDER = "8823111735959"; // 国内短信签名通道号

    public final static String TEMP_LATE_ID = "8a32d6f6e21d4278aad19a59c5872996"; // 模板ID

    public final static String SIGNATURE = "Newpark1"; // 签名名称

    public String receiver = "+8617507604042"; // 短信接收人号码

    public String statusCallBack = "";

    public String templateParas; // 模板变量，此处以单变量验证码短信为例，请客户自行生成6位验证码，并定义为字符串类型，以杜绝首位0丢失的问题（例如：002569变成了2569）。

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getStatusCallBack() {
        return statusCallBack;
    }

    public void setStatusCallBack(String statusCallBack) {
        this.statusCallBack = statusCallBack;
    }

    public String getTemplateParas() {
        return templateParas;
    }

    public void setTemplateParas(String templateParas) {
        this.templateParas = templateParas;
    }
}
