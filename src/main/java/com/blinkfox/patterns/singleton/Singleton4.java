package com.blinkfox.patterns.singleton;

/**
 * 通过使用静态内部类的方式来实现懒加载且线程安全的创建单例.
 *
 * @author blinkfox on 2017-10-23.
 */
public class Singleton4 {

    private Singleton4() {}

    /**
     * 静态内部类.
     */
    private static final class SingletonHolder {

        private SingletonHolder() {}

        private static Singleton4 instance = new Singleton4();

    }

    /**
     * 通过懒加载的方式获取Singleton唯一实例的方法.
     * @return Singleton实例
     */
    public static Singleton4 getInstance() {
        return SingletonHolder.instance;
    }

}