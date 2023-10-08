package com.newpark.mybatisplus.config;

import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * localDateTime类型处理器
 *
 * @Author longzhu
 * @Date 2023/4/26 16:57
 */
@Component
public class MybatisPlusLocalDateTimeTypeHandler extends LocalDateTimeTypeHandler {

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        if (object == null) {
            return null;
        }
        return super.getResult(rs, columnName);
    }
}
