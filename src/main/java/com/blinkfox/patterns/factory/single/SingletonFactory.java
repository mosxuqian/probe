package com.blinkfox.patterns.factory.single;

import java.lang.reflect.Constructor;

/**
 * 生成单例的工厂类
 * Created by blinkfox on 16-7-4.
 */
public class SingletonFactory {

    private static Singleton singleton;

    static {
        try {
            Class c = Class.forName(Singleton.class.getName());
            // 获得无参构造
            Constructor constructor = c.getDeclaredConstructor();
            // 设置无参构造是可访问的
            constructor.setAccessible(true);
            // 产生一个实例对象
            singleton = (Singleton) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成单例的工厂类方法中生成单例出错");
        }
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}