package com.newpark.im.service;

import com.alibaba.fastjson.JSON;
import com.newpark.pojo.UsersIm;

import java.util.List;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/12/23 11:27
 **/
public interface IImCoreService {

    JSON getClientConfig(String uId);

    JSON userRegister(List<UsersIm> usersIm);

    JSON groupGetGroupsInfo(List<String> groupIds);

}
