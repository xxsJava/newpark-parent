package com.newpark.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newpark.test.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxs18
 * @since 2023-10-08
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> getSysUserFindAll();

}
