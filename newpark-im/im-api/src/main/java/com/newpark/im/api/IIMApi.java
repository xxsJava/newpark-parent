package com.newpark.im.api;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.newpark.im.api.fallback.IMFallback;
import com.newpark.pojo.UsersIm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author xxs18
 * @Description 即时通讯接口
 * @Date 2024/1/2 16:07
 **/
@FeignClient(name = "newpark-im" , fallback = IMFallback.class)
public interface IIMApi {

    @RequestMapping(value = "/OpenIM/user_register",method = RequestMethod.POST)
    R<?> userRegister(@RequestBody List<UsersIm> usersIms);

}
