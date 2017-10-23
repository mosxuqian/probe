package com.blinkfox.patterns.singleton;

/**
 * 双重校验锁线程安全懒汉式.
 *
 * @author blinkfox on 2017-10-23.
 */
public class Singleton3 {

    private static Singleton3 singleton;

    private Singleton3() {}

    /**
     * 通过'双重校验锁'来更高效的保证线程安全，也是懒加载的方式来获取实例.
     * @return Singleton实例
     */
    public static Singleton3 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton3.class) {
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }

}