package com.newpark.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newpark.test.entity.SysUser;
import com.newpark.test.mapper.SysUserMapper;
import com.newpark.test.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxs18
 * @since 2023-10-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> getSysUserFindAll() {
        return sysUserMapper.getSysUserFindAll();
    }
}
