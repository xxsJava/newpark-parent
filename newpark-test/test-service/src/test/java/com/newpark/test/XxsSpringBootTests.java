package com.newpark.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/8/18 16:33
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class XxsSpringBootTests {

    @Before
    public void init() {
        log.info("开始测试-----------------");
    }

    @After
    public void after() {
        log.info("测试结束-----------------");
    }

}
