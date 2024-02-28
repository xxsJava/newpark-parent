package com.newpark.im.api.fallback;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.newpark.im.api.IIMApi;
import com.newpark.pojo.UsersIm;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author xxs18
 * @Description IM远程接口回调
 * @Date 2024/1/2 16:08
 **/
public class IMFallback implements IIMApi {
    @Override
    public R<?> userRegister(List<UsersIm> usersIms) {
        return R.failed("IM-注册繁忙！！！！");
    }
}
