package com.blinkfox.test.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关于颜色的枚举.
 * @author blinkfox on 2017/9/17.
 */
public enum ColorEnum {

    RED(1, "红色") {
        @Override
        public void paint() {
            log.info("使用了'红色'颜料来喷漆");
        }
    },

    GREEN(2, "绿色") {
        @Override
        public void paint() {
            log.info("使用了'绿色'颜料来喷漆");
        }
    },

    BLUE(3, "蓝色") {
        @Override
        public void paint() {
            log.info("使用了'蓝色'颜料来喷漆");
        }
    };

    private static final Logger log = LoggerFactory.getLogger(ColorEnum.class);

    /** 颜色的code. */
    private int code;

    /** 颜色的名称. */
    private String name;

    /**
     * 枚举的构造方法默认且只能是private的.
     * @param code 代码值
     * @param name 名称
     */
    ColorEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 使用不同的颜色来喷漆的抽象方法.
     */
    public abstract void paint();

    /**
     * 根据颜色的code值获取到对应的名称.
     * @param code 颜色code
     * @return 颜色名称
     */
    public static String getNameByCode(int code) {
        for (ColorEnum color: ColorEnum.values()) {
            if (color.code == code) {
                return color.name;
            }
        }
        return null;
    }

    /* getter方法. */

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 覆盖的toString方法.
     * @return 字符串
     */
    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }

}