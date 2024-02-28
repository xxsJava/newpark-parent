package com.newpark.sso.sys.utils;

import cn.hutool.crypto.digest.MD5;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.util.JwtUtil;
import com.newpark.core.utils.MapUtils;
import com.newpark.pojo.dto.SysUsrDto;
import com.newpark.redis.utils.RedisUtils;
import org.aspectj.apache.bcel.classfile.Code;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Author xxs18
 * @Description 生成验证码
 * @Date 2024/1/2 15:19
 **/
public class RandomSmsUtil {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 生成四位数验证码
     * @return
     */
    public static String getRandom(String phone,RedisUtils redisUtils){
        String sources = "123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 4; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        // 验证码存redis 5分钟过期
        redisUtils.set(PrefixMsgEnum.MSG_CODE.getMsg() + phone, flag.toString(), PrefixMsgEnum.REDIS_TIME_DAY_5.getTime());
        return flag.toString();
    }

}
