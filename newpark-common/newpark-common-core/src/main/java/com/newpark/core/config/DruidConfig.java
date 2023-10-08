package com.newpark.core.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid-慢sql，防火墙监控配置
 *
 * @author jack
 * @date 2023/4/17
 */
@Configuration
public class DruidConfig {


    @Bean
    public Slf4jLogFilter logFilter() {

        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setStatementExecutableSqlLogEnable(true);
        logFilter.setStatementLogEnabled(false);
        return logFilter;
    }

    @Bean
    public StatFilter statFilter() {

        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(1000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    /**
     * sql防火墙过滤器配置
     *
     * @param wallConfig
     * @return WallFilter
     */
    @Bean
    public WallFilter wallFilter(WallConfig wallConfig) {

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        //对被认为是攻击的SQL进行LOG.error输出
        wallFilter.setLogViolation(true);
        //对被认为是攻击的SQL抛出SQLException
        wallFilter.setThrowException(false);
        return wallFilter;
    }

    /**
     * sql防火墙配置
     *
     * @return WallConfig
     */
    @Bean
    public WallConfig wallConfig() {

        WallConfig wallConfig = new WallConfig();
        wallConfig.setAlterTableAllow(false);
        wallConfig.setCreateTableAllow(false);
        wallConfig.setDeleteAllow(true);
        wallConfig.setMergeAllow(false);
        wallConfig.setDescribeAllow(false);
        wallConfig.setShowAllow(false);
        return wallConfig;
    }
}
