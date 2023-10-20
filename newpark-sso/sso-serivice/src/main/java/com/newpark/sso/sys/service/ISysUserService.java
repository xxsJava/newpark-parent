package com.newpark.sso.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.base.model.vo.R;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.sys.entity.SysUser;

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
    R loginFind(LoginVo loginVo);
}
