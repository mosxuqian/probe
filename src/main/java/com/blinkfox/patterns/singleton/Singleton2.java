package com.blinkfox.patterns.singleton;

/**
 * 饿汉式1，非线程安全.
 *
 * @author blinkfox on 2017-10-23.
 */
public class Singleton2 {

    /** 全局唯一实例. */
    private static Singleton2 singleton;

    private Singleton2() {}

    /**
     * 通过懒加载的方式获取实例，但是非线程安全.
     * @return Singleton实例
     */
    public static Singleton2 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }

}