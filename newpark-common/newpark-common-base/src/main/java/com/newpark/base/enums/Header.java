package com.newpark.base.enums;

/**
 * @Author xxs18
 * @Description 请求头参数
 * @Date 2023/12/23 10:30
 **/
public enum Header {

    CONTENT_TOKEN("Content-TOKEN"),

    OPERATION_ID("operationID");

    private final String value;

    private Header(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return this.getValue();
    }

}
