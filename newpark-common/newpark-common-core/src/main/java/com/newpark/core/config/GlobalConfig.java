package com.newpark.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jack
 * @date 2023/3/14
 */
@Configuration
@MapperScan("com.newpark.core.mapper")
public class GlobalConfig {
}
