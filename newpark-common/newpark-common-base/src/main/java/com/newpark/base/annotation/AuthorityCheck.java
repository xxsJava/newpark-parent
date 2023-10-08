package com.newpark.base.annotation;


import com.newpark.base.enums.AuthorityCheckLogicalEnum;


import java.lang.annotation.*;

/**
 * @author jack
 * @date 2023/4/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityCheck {

    /**
     * 权限编码，如果没有值， 则仅校验是否登录
     *
     * @return
     */
    String[] value() default "";

    AuthorityCheckLogicalEnum logical() default AuthorityCheckLogicalEnum.OR;

}
