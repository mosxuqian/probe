package com.blinkfox.patterns.singleton;

/**
 * 饿汉式单例模式.
 *
 * @author blinkfox on 2017-10-23.
 */
public class Singleton {

    /** 全局唯一实例. */
    private static final Singleton singleton = new Singleton();

    private Singleton() {}

    public static Singleton getSingleton() {
        return singleton;
    }

}