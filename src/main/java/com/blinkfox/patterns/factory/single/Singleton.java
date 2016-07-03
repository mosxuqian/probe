package com.blinkfox.patterns.factory.single;

/**
 * 工厂方法模式中的单例类
 * Created by blinkfox on 16-7-4.
 */
public class Singleton {

    /**
     * 私有化构造方法，不允许new产生一个对象
     */
    private Singleton() {}

    /**
     * 工厂方法模式中的单例模式业务方法
     */
    public void doSomething() {
        System.out.println("执行工厂方法单例模式的方法。。。");
    }

}