package com.newpark.im.enums;

import lombok.Getter;

/**
 * @Author xxs18
 * @Description 平台类型
 * @Date 2023/12/23 11:14
 **/
@Getter
public enum PlatformID {

    IOS(1), ANDROID(2), WINDOWS(3), OSX(4), WEB(5), XCX(6), ANDROID_PAD(7), IPAD(8), ADMIN(10);

    private final Integer value;

    private PlatformID(Integer value) {
        this.value = value;
    }

}
