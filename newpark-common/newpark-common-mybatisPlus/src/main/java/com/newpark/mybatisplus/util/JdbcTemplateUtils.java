package com.newpark.mybatisplus.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ObjectUtils;

/**
 * 获取jdbcTemplate工具类
 * @author jack
 * @date  2023/3/14
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public synchronized  static JdbcTemplate getJdbcTemplate() {
        if (ObjectUtils.isEmpty(jdbcTemplate)) {
            SqlSessionFactory sqlSessionFactory = SpringContextHolder.getBean(SqlSessionFactory.class);
            jdbcTemplate = new JdbcTemplate(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource());
        }
        return jdbcTemplate;
    }
}
