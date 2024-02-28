package com.newpark.sso.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.newpark.pojo.dto.SysUsrDto;
import com.newpark.sso.entity.vo.LoginVo;
import com.newpark.sso.entity.vo.RegisteredVo;
import com.newpark.sso.sys.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
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
    SysUsrDto loginFind(LoginVo loginVo);

    /**
     * @description: 用户是否存在
     **/
    SysUsrDto isUser(LoginVo loginVo);

    /**
     * 创建账号
     * @param registeredVo
     * @return
     */
    @Insert("INSERT INTO `sys_user` (`u_id`, `u_phone`, `u_start_time`,`u_pass`) VALUES (#{uId}, #{uPhone}, #{uStartTime},#{pass})")
    Integer sysUsrIns(RegisteredVo registeredVo);

    @Insert("INSERT INTO `usr_info` (`info_id`, `u_path`, `u_nikname`, `u_signature`, `school_id`,`u_sex`,`u_lab`) VALUES (#{uId}, #{uPath}, #{uNikname}, #{description}, #{schoolId},#{uSex},#{uLab})")
    Integer usrInfoIns(RegisteredVo registeredVo);

    @Insert("INSERT INTO `usr_vip` (`v_id`, `v_type`, `v_status`, `v_level`, `v_points`, `v_start_time`, `v_end_time`) VALUES (#{uId}, 'NOT', 'NOT', 0, 0, 0, 0)")
    Integer usrVipIns(RegisteredVo registeredVo);
}
