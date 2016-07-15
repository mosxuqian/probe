package com.blinkfox.patterns.observer;

/**
 * 具体的观察者
 * Created by blinkfox on 16/7/15.
 */
public class ConcreteObserver implements Observer {

    /**
     * 实现更新方法
     */
    @Override
    public void update() {
        System.out.println("接收到信息,并进行处理...");
    }

}