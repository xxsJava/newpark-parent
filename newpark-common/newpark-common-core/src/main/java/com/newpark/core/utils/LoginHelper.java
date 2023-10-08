package com.newpark.core.utils;


import com.alibaba.fastjson.JSON;
import com.newpark.base.constant.Constant;
import com.newpark.base.constant.SystemId;
import com.newpark.base.enums.AuthorityCheckLogicalEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.exception.BizException;
import com.newpark.base.model.entity.UserDetailEntity;
import com.newpark.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author jack
 * @date 2023/4/13
 */
@Component
@Slf4j
public class LoginHelper {

    private static final String SESSION = "session:";

    private static ThreadLocal<UserDetailEntity> threadLocal = new ThreadLocal<>();

    private static final String CLIENT_LOGIN_TOKEN = "zh-share-token";

    public static void clearThreadLocal() {
        threadLocal.remove();
    }

    @Autowired
    private static RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redis) {
        LoginHelper.redisUtils = redis;
    }

    private static RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public void setRedisConnectionFactory(RedisConnectionFactory facktory) {
        LoginHelper.redisConnectionFactory = facktory;
    }


    /**
     * 获取当前用户
     *
     * @return
     */
    private static UserDetailEntity getCurrentUser(boolean isThrowException) {

        UserDetailEntity userInfoDto;
        //先从当前线程获取，同一线程不需要频繁访问redis
        userInfoDto = threadLocal.get();
        if (userInfoDto != null) {
            return userInfoDto;
        }
        //获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取 header 的token 值
        String token = request.getHeader(CLIENT_LOGIN_TOKEN);
        log.info(token);
        //没有token 则是没有登陆
        if (StringUtils.isBlank(token)) {
            //是否抛异常
            if (isThrowException == true) {
                throw new BizException(ResponseCodeEnum.TOKEN_FAULT.getCode(), ResponseCodeEnum.TOKEN_FAULT.getMsg() + " no token");
            } else {
                return null;
            }
        }

        LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisConnectionFactory;
        lettuceConnectionFactory.setDatabase(0);
        RedisConnection connection = lettuceConnectionFactory.getConnection();
        byte[] sessionKey = String.format("%1$s%2$s", SESSION, token).getBytes();
        byte[] accessResult = connection.get(sessionKey);

        if (accessResult == null || accessResult.length < 1) {
            throw new BizException("令牌无效，请联系管理员");
        }
        String json = new String(accessResult);
        userInfoDto = JSON.parseObject(json, UserDetailEntity.class);

        //如果没有获取到，则已经失效。需求重新登录
        if (userInfoDto == null) {
            if (isThrowException == true) {
                throw new BizException(ResponseCodeEnum.TOKEN_FAULT.getCode(), ResponseCodeEnum.TOKEN_FAULT.getMsg() + " invalid");
            }
        } else {
            //存入当前线程的共享池中
            threadLocal.set(userInfoDto);
        }
        //返回结果
        return userInfoDto;
    }


    /**
     * 获取当前用户
     *
     * @return
     */
    public static UserDetailEntity getCurrentUserAndCheck() {
        return getCurrentUser(true);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static UserDetailEntity getCurrentUser() {
        return getCurrentUser(true);
    }


    /**
     * 校验是否有权限
     * 传多个 编码 就是 or ，或
     *
     * @param authorityCode
     * @return
     */
    public static Boolean checkAuthorityForOr(String... authorityCode) {
        UserDetailEntity userDetailEntity = getCurrentUser();
        return checkAuthority(userDetailEntity, AuthorityCheckLogicalEnum.OR, authorityCode);
    }

    /**
     * 校验是否有权限
     * 传多个 编码 就是 and ，与
     *
     * @param authorityCode
     * @return
     */
    public static Boolean checkAuthorityForAnd(String... authorityCode) {
        UserDetailEntity userDetailEntity = getCurrentUser();
        return checkAuthority(userDetailEntity, AuthorityCheckLogicalEnum.AND, authorityCode);
    }

    /**
     * 校验是否有权限
     *
     * @param authorityCode
     * @return
     */
    public static Boolean checkAuthority(AuthorityCheckLogicalEnum logical, String... authorityCode) {
        UserDetailEntity userDetailEntity = getCurrentUser();
        return checkAuthority(userDetailEntity, logical, authorityCode);
    }

    /**
     * 校验是否有权限
     *
     * @param authorityCodes
     * @return
     */
    public static Boolean checkAuthority(UserDetailEntity userDetailEntity, AuthorityCheckLogicalEnum logical, String... authorityCodes) {
        //不传编码？ 当你没权限
        if (authorityCodes == null || authorityCodes.length <= 0) {
            return false;
        }
        //没有用户信息，肯定是没权限了啊
        if (userDetailEntity == null) {
            return false;
        }
        //赋值权限码列表
        Set<String> permSet = userDetailEntity.getPermSet();
        //没有赋予权限，那就false
        if (permSet == null || permSet.isEmpty()) {
            return false;
        }
        //是否有命中一个权限码
        Boolean isHasOneAuthority = false;
        //判断是否有权限
        for (String authorityCode : authorityCodes) {
            //如果是有权限的
            if (permSet.contains(authorityCode)) {
                //如果是 or 或， 有一个权限码中了，就返回 true，不用再判断了
                if (AuthorityCheckLogicalEnum.OR.getCode().equals(logical.getCode())) {
                    return true;
                }
                //命中一次，就设置为true
                isHasOneAuthority = true;
            } else {
                //如果是and 与 ，但是当前权限码没有命中，则返回 false， 不用再继续判断了
                if (AuthorityCheckLogicalEnum.AND.getCode().equals(logical.getCode())) {
                    return false;
                }
            }
        }
        //都经过特殊判断后，就返回 是否中过权限码
        return isHasOneAuthority;
    }

    /**
     * 判断管理员
     *
     * @return
     */
    public static Boolean isAdmin() {
        boolean result = false;
        if (getCurrentUser().getRoleCode().equals(Constant.Role.ADMIN.getCode())) {
            result = true;
        }
        return result;
    }

    /**
     * 判断超级管理员
     *
     * @return
     */
    public static Boolean isSuperAdmin() {
        boolean result = false;
        if (getCurrentUser().getUserId().equals(SystemId.SUPER_ADMIN)) {
            result = true;
        }
        return result;
    }

    /**
     * 判断是否系统类型角色
     *
     * @return
     */
    public static Boolean isSystemTypeRole() {
        boolean result = false;
        if (getCurrentUser().getRoleType().equals(Constant.STRING_ONE)) {
            result = true;
        }
        return result;
    }






}
