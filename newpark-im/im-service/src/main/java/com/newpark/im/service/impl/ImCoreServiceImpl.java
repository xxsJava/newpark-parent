package com.newpark.im.service.impl;

import com.alibaba.fastjson.JSON;
import com.newpark.im.config.ImConfig;
import com.newpark.im.config.ImCoreReqConfig;
import com.newpark.im.enums.PlatformID;
import com.newpark.im.service.IImCoreService;
import com.newpark.pojo.UsersIm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/23 11:28
 **/
@Service
public class ImCoreServiceImpl implements IImCoreService {

    @Resource
    private ImCoreReqConfig imCoreReqConfig;

    @Resource
    private ImConfig imConfig;

    @Override
    public JSON getClientConfig(String uId) {
        System.out.println(uId);
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("secret","openIM123");
        paramsMap.put("platformID", PlatformID.ANDROID.getValue());
        paramsMap.put("userID", "openIM123456");
        return JSON.parseObject(imCoreReqConfig.imPost(paramsMap,"/auth/user_token"));
    }

    @Override
    public JSON userRegister(List<UsersIm> usersIm) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("secret",imConfig.getSecret());
        paramsMap.put("users",usersIm);

        return JSON.parseObject(imCoreReqConfig.imPost(paramsMap,"/user/user_register"));
    }

    @Override
    public JSON groupGetGroupsInfo(List<String> groupIds) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("secret",imConfig.getSecret());
        paramsMap.put("groupIDs",groupIds);

        return JSON.parseObject(imCoreReqConfig.imPost(paramsMap,"/group/get_groups_info","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySUQiOiJvcGVuSU0xMjM0NTYiLCJQbGF0Zm9ybUlEIjoyLCJleHAiOjE3MTIyMjMwMzcsIm5iZiI6MTcwNDQ0NjczNywiaWF0IjoxNzA0NDQ3MDM3fQ.XhAqxP6oZOOgPw-_33aRgWCYs61nkcW8H1_lEgljT9c"));
    }
}
