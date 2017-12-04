package com.blinkfox.test.other.xml;

/**
 * Gender性别枚举类.
 *
 * @author blinkfox on 2017-12-04.
 */
public enum Gender {

    MALE(true),

    FEMALE (false);

    private boolean code;

    /**
     * 构造方法.
     * @param code 性别值
     */
    Gender(boolean code) {
        this.code = code;
    }

}