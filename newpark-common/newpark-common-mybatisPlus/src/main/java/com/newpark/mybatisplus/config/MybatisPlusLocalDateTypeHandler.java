package com.newpark.mybatisplus.config;

import org.apache.ibatis.type.LocalDateTypeHandler;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * localDate类型处理器
 *
 * @Author longzhu
 * @Date 2023/4/26 16:58
 */
@Component
public class MybatisPlusLocalDateTypeHandler extends LocalDateTypeHandler {

    @Override
    public LocalDate getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (object == null) {
            return null;
        }
        return super.getResult(rs, columnName);
    }
}
