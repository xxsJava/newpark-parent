package com.newpark.sso.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xxs18
 * @since 2023-10-18
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * @description: 登录
     **/
    SysUser loginFind(LoginVo loginVo);

    /**
     * @description: 用户是否存在
     **/
    SysUser isUser(LoginVo loginVo);
}
