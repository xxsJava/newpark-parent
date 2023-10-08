package com.newpark.mybatisplus.enums;

/**
 * 数据权限值枚举类
 */
public enum SysPermFieldValEnum {

    LoginUser(1,"当前登录用户"),

    Empty(8,"空");

    private Integer code;
    private String description;

    SysPermFieldValEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

}
