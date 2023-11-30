package com.newpark.test;

import cn.hutool.crypto.digest.MD5;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/10/9 14:09
 **/
public class JwtTest extends XxsSpringBootTests {



    @Test
    public void md5Test(){
        System.out.println(MD5.create().digestHex16("12345678"));
    }



}
