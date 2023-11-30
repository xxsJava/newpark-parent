package com.newpark.common.enums;/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/6 10:50
 **/

import lombok.Getter;

/**
 * @Author xxs18
 * @Description 秘钥
 * @Date 2023/11/6 10:50
 **/
@Getter
public enum KeySm2 {

    PUBLIC_KEY("3059301306072a8648ce3d020106082a811ccf5501822d03420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40"),
    PRIVATE_KEY("308193020100301306072a8648ce3d020106082a811ccf5501822d0479307702010104209135a0dd817cc935ebcd9d6c0551cffea903ab3fbabc8009b35d2037edd80123a00a06082a811ccf5501822da14403420004f16494746de1bfcda0ed267c214f7440fe32766d302217c303399fbfce220687e6c62f4a38fe9c792018cd9797a3cf4d933a0f0c92cc378169c58e6d072cad40");

    private String key;

    KeySm2(String key) {
        this.key = key;
    }
}
