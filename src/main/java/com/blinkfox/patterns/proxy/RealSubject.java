package com.blinkfox.patterns.proxy;

/**
 * 真实主题类
 * Created by blinkfox on 2017/1/1.
 */
public class RealSubject implements ISubject {

    /**
     * 实现方法
     */
    @Override
    public void request() {
        System.out.println("真实主题类请求方法...");
    }

}