package com.newpark.sso.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.entity.vo.SchoolParamVo;
import com.newpark.sso.entity.vo.SmsLoginVo;
import com.newpark.sso.sys.entity.SysUser;
import com.newpark.sso.sys.entity.UsrInfo;
import com.newpark.sso.sys.entity.UsrSchool;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * @description: 登录
     **/
    R<?> loginFind(LoginVo loginVo);

    /**
     * @description: 注册账号
     **/
    R<?> registeredUser(RegisteredVo registeredVo);

    /**
     * @description: 验证码登录验证
     **/
    R<?> smsLoginFind(SmsLoginVo smsLoginVo);

    /**
     * 1.学校查询
     * @param schoolParamVo
     * @return
     */
    List<UsrSchool> schoolFindAll(SchoolParamVo schoolParamVo);

    /**
     * 1.查询用户信息
     * @param token
     * @return
     */
    UsrInfo usrInfoFindById(String token);
}
