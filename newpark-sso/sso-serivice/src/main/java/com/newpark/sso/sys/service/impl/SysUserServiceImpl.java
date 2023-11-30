package com.newpark.sso.sys.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.core.utils.DateUtils;
import com.newpark.sso.config.IdGeneratorSnowflake;
import com.newpark.core.utils.MapUtils;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.sso.entity.dto.SysUsrDto;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.entity.vo.SmsLoginVo;
import com.newpark.sso.entity.vo.SysUsrVo;
import com.newpark.sso.sys.entity.SysRoleDivide;
import com.newpark.sso.sys.entity.SysUser;
import com.newpark.sso.sys.mapper.SysRoleDivideMapper;
import com.newpark.sso.sys.mapper.SysUserMapper;
import com.newpark.sso.sys.service.ISysUserService;
import com.newpark.base.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
    private SysRoleDivideMapper sysRoleDivideMapper;
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Override
    public R loginFind(LoginVo loginVo) {
        try {
            // 加密对比
            loginVo.setPassword(MD5.create().digestHex16(loginVo.getPassword().trim()));

            SysUsrDto sysUser = sysUserMapper.loginFind(loginVo);
            if (sysUser != null) {
                // 登录成功返回token
                return R.ok(verIfUsr(sysUser));
            }
        } catch (Exception e) {
            log.error("登录错误--->{}", e);
            // 用户是否存在
            return isUser(loginVo);
        }
        // 用户是否存在
        return isUser(loginVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R registeredUser(RegisteredVo registeredVo) {
        // 验证码进行失效
        redisUtils.delete(PrefixMsgEnum.MSG_CODE.getMsg() + registeredVo.getUPhone());

        LoginVo loginVo = new LoginVo();
        loginVo.setUPhone(registeredVo.getUPhone());
        SysUsrDto sysUser = sysUserMapper.isUser(loginVo);

        if (sysUser != null) {
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }

        // 生成id
        Long sysUsrId = idGeneratorSnowflake.snowflakeId();
        Long usrInfoId = idGeneratorSnowflake.snowflakeId();
        Long usrVipId = idGeneratorSnowflake.snowflakeId();

        // 创建普通账号账户
        // 设置唯一id
        // 创建个人信息
        // 创建会员信息
        registeredVo.setUId(sysUsrId);
        registeredVo.setUStartTime(DateUtils.getTimes());

        // 创建权限、个人、会员分配信息、默认学生
        SysRoleDivide sysRoleDivide = new SysRoleDivide();
        sysRoleDivide.setUId(sysUsrId);
        sysRoleDivide.setInfoId(usrInfoId);
        sysRoleDivide.setVipId(usrVipId);
        sysRoleDivide.setRoleId(100000L);

        try {
            sysUserMapper.sysUsrIns(registeredVo);
            registeredVo.setUId(usrInfoId);
            sysUserMapper.usrInfoIns(registeredVo);
            registeredVo.setUId(usrVipId);
            sysUserMapper.usrVipIns(registeredVo);
            sysRoleDivideMapper.insert(sysRoleDivide);
            return R.failed(ResponseCodeEnum.CREATED);
        } catch (Exception e) {
            log.error("验证码: {}", e);
            return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
        }
    }

    @Override
    public R smsLoginFind(SmsLoginVo smsLoginVo) {
        LoginVo loginVo = new LoginVo();
        loginVo.setUPhone(smsLoginVo.getPhone());

        SysUsrDto sysUser = sysUserMapper.isUser(loginVo);
        if (sysUser != null) {
            // 存在直接登录
            return R.ok(verIfUsr(sysUser));
        }

        return R.ok();
    }

    /**
     * @description: 用户是否存在 不存在发送验证码创建新用户
     **/
    public R isUser(LoginVo loginVo) {

        SysUsrDto sysUser = sysUserMapper.isUser(loginVo);

        if (sysUser != null) {
            // 存在 登录失败账号密码错误
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }

        // 不存在发送短信验证码
        log.info("发送验证码,创建新用户");
        snedSms(loginVo.getUPhone());

        return R.failed(ResponseCodeEnum.LOGIN_USER_NO_EXIST);
    }

    /**
     * @description: 登录成功 数据加密存储
     **/
    private StringBuffer verIfUsr(SysUsrDto sysUser) {
        StringBuffer tokenKey = new StringBuffer(PrefixMsgEnum.MSG_PR_TOKEN.getMsg());
        tokenKey.append(MD5.create().digestHex16(System.currentTimeMillis() + ""));
        // 加密
        String token = JwtUtil.getToken(MapUtils.objectToMap(sysUser));

        // 明文存redis
        // 登录成功用户数据存redis 设置7天过期
        redisUtils.set(PrefixMsgEnum.MSG_USR.getMsg() + sysUser.getUId(), sysUser,
            PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        // 登录成功 token 密文存redis 设置7天过期
        redisUtils.set(tokenKey.toString(), token, PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());

        return tokenKey;
    }

    /**
     * @description: 发送验证码
     **/
    private void snedSms(String phoen) {
        // 验证码存redis 5分钟过期
        redisUtils.set(PrefixMsgEnum.MSG_CODE.getMsg() + phoen, 1234, PrefixMsgEnum.REDIS_TIME_DAY_5.getTime());
    }
}
