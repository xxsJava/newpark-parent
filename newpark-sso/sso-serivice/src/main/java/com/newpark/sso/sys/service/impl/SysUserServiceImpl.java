package com.newpark.sso.sys.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.MapUtils;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.sys.entity.SysUser;
import com.newpark.sso.sys.mapper.SysUserMapper;
import com.newpark.sso.sys.service.ISysUserService;
import com.newpark.base.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public R loginFind(LoginVo loginVo) {

        String token;

        StringBuffer tokenKey = new StringBuffer("token_pr_newpark_");
        tokenKey
            .append(MD5.create().digestHex16(System.currentTimeMillis()+""));

        try {
            @NotEmpty
            SysUser sysUser = sysUserMapper.loginFind(loginVo);

            // 加密
            token = JwtUtil.getToken(MapUtils.objectToMap(sysUser));

            // 明文存redis
            // 登录成功用户数据存redis 设置7天过期
            redisUtils.set("usr_" + sysUser.getUId(), sysUser, 60 * 24 * 7);
            // 登录成功 token 密文存redis 设置7天过期
            redisUtils.set(tokenKey.toString(), token, 60 * 24 * 7);
        } catch (Exception e) {
            //用户是否存在
            return isUser(loginVo);
        }
        return R.ok(tokenKey);
    }


    /**
     * @description: 用户是否存在
     **/
    public R isUser(LoginVo loginVo){
        try {
            @NotEmpty
            SysUser sysUser = sysUserMapper.isUser(loginVo);
            //存在 登录失败账号密码错误
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }catch (Exception e) {
            //不存在发送短信验证码
            log.info("发送验证码,创建新用户");
            return R.failed(ResponseCodeEnum.LOGIN_USER_NO_EXIST);
        }
    }
}
