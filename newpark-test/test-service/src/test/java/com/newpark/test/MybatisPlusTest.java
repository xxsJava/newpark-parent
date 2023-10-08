package com.newpark.test;

import com.newpark.redis.utils.RedisUtils;
import com.newpark.test.mapper.SysUserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/8 13:53
 **/
public class MybatisPlusTest extends XxsSpringBootTests {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testFindAll(){
        System.out.println(sysUserMapper.getSysUserFindAll());
    }

    @Test
    public void testRedis() {
        System.out.println(redisUtils.get("name"));
    }

}
