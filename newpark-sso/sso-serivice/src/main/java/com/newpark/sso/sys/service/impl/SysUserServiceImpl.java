package com.newpark.sso.sys.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.sso.config.IdGeneratorSnowflake;
import com.newpark.core.utils.MapUtils;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.sys.entity.SysUser;
import com.newpark.sso.sys.mapper.SysUserMapper;
import com.newpark.sso.sys.service.ISysUserService;
import com.newpark.base.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public R loginFind(LoginVo loginVo) {

        String token;
        StringBuffer tokenKey = new StringBuffer(PrefixMsgEnum.MSG_PR_TOKEN.getMsg());
        tokenKey.append(MD5.create().digestHex16(System.currentTimeMillis() + ""));

        try {
            //加密对比
            loginVo.setPassword(MD5.create().digestHex16(loginVo.getPassword().trim()));
            @NotEmpty
            SysUser sysUser = sysUserMapper.loginFind(loginVo);

            // 加密
            token = JwtUtil.getToken(MapUtils.objectToMap(sysUser));

            // 明文存redis
            // 登录成功用户数据存redis 设置7天过期
            redisUtils.set(PrefixMsgEnum.MSG_USR.getMsg() + sysUser.getUId(), sysUser,
                PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
            // 登录成功 token 密文存redis 设置7天过期
            redisUtils.set(tokenKey.toString(), token, PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        } catch (Exception e) {
            log.error("登录错误--->{}",e);
            // 用户是否存在
            return isUser(loginVo);
        }
        return R.ok(tokenKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R registeredUser(RegisteredVo registeredVo) {
        //验证码进行失效
        redisUtils.delete(PrefixMsgEnum.MSG_CODE.getMsg()+registeredVo.getUPhone());

        //设置唯一id
        registeredVo.setUId(idGeneratorSnowflake.snowflakeId());
        registeredVo.setPass(MD5.create().digestHex16(registeredVo.getPass().trim()));

        try {
            return R.ok(sysUserMapper.registeredUser(registeredVo));
        }catch (Exception e) {
            log.error("验证码: {}",e);
            return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
        }
    }

    /**
     * @description: 用户是否存在 不存在发送验证码创建新用户
     **/
    public R isUser(LoginVo loginVo) {

        SysUser sysUser = sysUserMapper.isUser(loginVo);

        if (sysUser != null) {
            // 存在 登录失败账号密码错误
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }

        // 不存在发送短信验证码
        log.info("发送验证码,创建新用户");
        // 验证码存redis 5分钟过期
        redisUtils.set(PrefixMsgEnum.MSG_CODE.getMsg() + loginVo.getUPhone(), 1234, PrefixMsgEnum.REDIS_TIME_DAY_5.getTime());

        return R.failed(ResponseCodeEnum.LOGIN_USER_NO_EXIST);

    }
}
