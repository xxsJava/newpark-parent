package com.newpark.base.vali;

import lombok.Getter;

/**
 * @Author xxs18
 * @Description 校验参数静态信息
 * @Date 2023/11/17 11:39
 **/

public class ValidatedStrMsg {

    public final static String NOT_NULL_MSG = "参数不能空";

    public final static String ERROR_MSG = "参数错误";

    public final static String PAGE_SIZE_MSG = "页大小不要篡改哦";

    public final static String DATE_TIME_MSG = "时间不要篡改哦";

    public final static String RANGE_MSG = "超出取值范围";

    public final static String PASS_MSG =
        "长度为 8 到 20 个字符\n" + "必须包含至少一个大写字母\n" + "必须包含至少一个小写字母\n" + "必须包含至少一个数字\n" + "可以包含特殊字符（例如：!@#$%^&*）";

    public final static String PHONE_MSG = "手机号错误";

}
