package com.newpark.base.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author xxs18
 * @Description 消息前缀
 * @Date 2023/10/20 14:25
 **/
@Getter
public enum PrefixMsgEnum {

    /**
     * @description: 验证码
     **/
    MSG_CODE("msg_code_"),

    /**
     * @description: 用户数据
     **/
    MSG_USR("usr_"),

    /**
     * @description: 用户token
     **/
    MSG_PR_TOKEN("token_pr_newpark_"),
    /**
     * @description: redis 过期时间 60s
     **/
    REDIS_TIME_DAY_60(60)
    ,
    /**
     * @description: redis 过期时间 5m
     **/
    REDIS_TIME_DAY_5(PrefixMsgEnum.REDIS_TIME_DAY_60.getTime()*5),
    /**
     * @description: redis 过期时间 1day
     **/
    REDIS_TIME_DAY(PrefixMsgEnum.REDIS_TIME_DAY_60.getTime()*24),
    /**
     * @description: redis 过期时间 7day
     **/
    REDIS_TIME_7_DAY(PrefixMsgEnum.REDIS_TIME_DAY.getTime()*7),
    /**
     * @description: redis 过期时间 30day
     **/
    REDIS_TIME_30_DAY(PrefixMsgEnum.REDIS_TIME_DAY.getTime()*30);



    private String msg;

    private Integer time;

    PrefixMsgEnum(String msg) {
        this.msg = msg;
    }

    PrefixMsgEnum(Integer time) {
        this.time = time;
    }
}
