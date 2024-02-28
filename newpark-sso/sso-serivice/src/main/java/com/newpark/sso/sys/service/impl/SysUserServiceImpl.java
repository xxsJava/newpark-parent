package com.newpark.sso.sys.service.impl;

import static com.newpark.base.enums.PrefixMsgEnum.USR_INFO;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.base.enums.PrefixMsgEnum;
import com.newpark.base.enums.ResponseCodeEnum;
import com.newpark.base.model.vo.R;
import com.newpark.base.util.JwtUtil;
import com.newpark.core.utils.DateUtils;
import com.newpark.core.utils.MapUtils;
import com.newpark.core.utils.StringUtils;
import com.newpark.im.api.IIMApi;
import com.newpark.pojo.UsersIm;
import com.newpark.pojo.dto.SysUsrDto;
import com.newpark.redis.utils.RedisUtils;
import com.newpark.service.ISmsHWYService;
import com.newpark.sso.config.IdGeneratorSnowflake;
import com.newpark.sso.entity.dto.SysUsrTokenDto;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.entity.vo.SchoolParamVo;
import com.newpark.sso.entity.vo.SmsLoginVo;
import com.newpark.sso.sys.entity.SysRoleDivide;
import com.newpark.sso.sys.entity.SysUser;
import com.newpark.sso.sys.entity.UsrInfo;
import com.newpark.sso.sys.entity.UsrSchool;
import com.newpark.sso.sys.mapper.SysRoleDivideMapper;
import com.newpark.sso.sys.mapper.SysUserMapper;
import com.newpark.sso.sys.mapper.UsrInfoMapper;
import com.newpark.sso.sys.mapper.UsrSchoolMapper;
import com.newpark.sso.sys.service.ISysUserService;
import com.newpark.sso.sys.utils.RandomSmsUtil;

import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;

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
    private UsrSchoolMapper usMapper;
    @Resource
    private RedisUtils redisUtils;

    @Resource
    private UsrInfoMapper userInfoMapper;
    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @Resource
    private IIMApi imApi;

    @Override
    public R<?> loginFind(LoginVo loginVo) {
        try {
            // 加密对比
            loginVo.setPassword(MD5.create().digestHex16(loginVo.getPassword().trim()));

            SysUsrDto sysUser = sysUserMapper.loginFind(loginVo);
            if (sysUser != null) {
                SysUsrTokenDto sysUserToken = SysUsrTokenDto.create();
                sysUserToken.setUsrToken(verIfUsr(sysUser).toString());
                sysUserToken.setUId(String.valueOf(sysUser.getUId()));
                // 登录成功返回token
                return R.ok(sysUserToken);
            }
        } catch (Exception e) {
            log.error("登录错误--->", e);
            // 用户是否存在
            return isUser(loginVo);
        }
        // 用户是否存在
        return isUser(loginVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<?> registeredUser(RegisteredVo registeredVo) {
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
        registeredVo.setPass(MD5.create().digestHex16(registeredVo.getPass()));

        // 创建权限、个人、会员分配信息、默认学生
        SysRoleDivide sysRoleDivide = new SysRoleDivide();
        sysRoleDivide.setUId(sysUsrId);
        sysRoleDivide.setInfoId(usrInfoId);
        sysRoleDivide.setVipId(usrVipId);
        sysRoleDivide.setRoleId(100000L);

        //OpenIm账号打通
        List<UsersIm> usersIms = new ArrayList<>();
        UsersIm usersIm = new UsersIm();
        usersIm.setUserID(sysUsrId.toString());
        usersIm.setNickname(registeredVo.getUNikname());
        usersIm.setFaceURL(registeredVo.getUPath());
        usersIms.add(usersIm);
        imApi.userRegister(usersIms);

        try {
            sysUserMapper.sysUsrIns(registeredVo);
            registeredVo.setUId(usrInfoId);
            sysUserMapper.usrInfoIns(registeredVo);
            registeredVo.setUId(usrVipId);
            sysUserMapper.usrVipIns(registeredVo);
            sysRoleDivideMapper.insert(sysRoleDivide);
            return R.failed(ResponseCodeEnum.CREATED);
        } catch (Exception e) {
            log.error("验证码: ", e);
            return R.failed(ResponseCodeEnum.PARAMETER_ERROR);
        }
    }

    @Override
    public R<?> smsLoginFind(SmsLoginVo smsLoginVo) {
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
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public UsrInfo usrInfoFindById(String token) {
        long uId;
        try {
            uId = Long.parseLong(JwtUtil.getToken(token).getClaim("uId").asString());
        } catch (UnsupportedEncodingException e) {
            return new UsrInfo();
        }

        if(StringUtils.isNotEmpty(redisUtils.get(USR_INFO.getMsg()+uId))){
            return JSON.parseObject(redisUtils.get(USR_INFO.getMsg()+uId), UsrInfo.class);
        }

        QueryWrapper<UsrInfo> queryWrap = new QueryWrapper<>();
        queryWrap.eq("info_id",uId);

        UsrInfo userInfo = userInfoMapper.selectOne(queryWrap);

        redisUtils.set(USR_INFO.getMsg()+uId,userInfo,PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        return userInfo;
    }

    @Override
    public List<UsrSchool> schoolFindAll(SchoolParamVo schoolParamVo) {
        QueryWrapper<UsrSchool> queryWrap = new QueryWrapper<>();
        queryWrap.eq(StringUtils.isNotEmpty(schoolParamVo.getSchoolCode()),"school_id",schoolParamVo.getSchoolCode());
        queryWrap.eq(StringUtils.isNotEmpty(schoolParamVo.getSchoolName()),"school_name",schoolParamVo.getSchoolName());
        List<UsrSchool> schoolLists = usMapper.selectList(queryWrap);
        redisUtils.set("schools",schoolLists,PrefixMsgEnum.REDIS_TIME_7_DAY.getTime());
        return schoolLists;
    }

    /**
     * @description: 用户是否存在 不存在发送验证码创建新用户
     **/
    public R<?> isUser(LoginVo loginVo) {
        SysUsrDto sysUser = sysUserMapper.isUser(loginVo);
        if (sysUser != null) {
            // 存在 登录失败账号密码错误
            return R.failed(ResponseCodeEnum.LOGIN_FAILURE);
        }
        // 不存在发送短信验证码
        log.info("发送验证码,创建新用户");
        ISmsHWYService.create().sendSms(loginVo.getUPhone(), RandomSmsUtil.getRandom(loginVo.getUPhone(),redisUtils));
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
}
