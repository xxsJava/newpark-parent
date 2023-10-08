package com.newpark.base.enums;

/**
 * @author jack
 * @date 2023/3/14
 */
public enum AuthorityCheckLogicalEnum {
    AND(0, "与"),
    OR(1, "或");


    private Integer code;
    private String desc;

    private AuthorityCheckLogicalEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


    public static String getDescByCode(Integer code) {
        for (AuthorityCheckLogicalEnum value : AuthorityCheckLogicalEnum.values()) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "";
    }

    public static AuthorityCheckLogicalEnum getEnumByCode(Integer code) {
        for (AuthorityCheckLogicalEnum value : AuthorityCheckLogicalEnum.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return OR;
    }

}
