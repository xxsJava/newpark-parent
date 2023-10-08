package com.newpark.mybatisplus.annotation;

import java.lang.annotation.*;

/**
 * 数据权限实现的注解
 * @author jack
 * @date 2023/3/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataAuth {

    /**
     * 菜单id
     * @return
     */
    String menuId() default "";

    /**
     * 表名
     * @return
     */
    String tableName() default "";

}
