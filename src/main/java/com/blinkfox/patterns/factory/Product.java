package com.blinkfox.patterns.factory;

/**
 * 抽象产品类
 * Created by blinkfox on 16-6-29.
 */
public abstract class Product {

    /**
     * 产品类的公共方法
     */
    public void method1() {
        System.out.println("这是产品类的公共方法");
    }

    /**
     * 抽象方法
     */
    public abstract void method2();

}
