package com.newpark.test.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newpark.test.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-08
 */
public interface ISysUserService extends IService<SysUser> {

    public List<SysUser> getSysUserFindAll();

}
