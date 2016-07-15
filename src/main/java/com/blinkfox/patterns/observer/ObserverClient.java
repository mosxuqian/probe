package com.blinkfox.patterns.observer;

/**
 * 观察者模式客户端场景类
 * Created by blinkfox on 16/7/15.
 */
public class ObserverClient {

    public static void main(String[] args) {
        // 创建一个被观察者和观察者
        ConcreteSubject sub = new ConcreteSubject();
        Observer obs = new ConcreteObserver();
        // 观察者观察被观察者
        sub.addObserver(obs);
        // 观察者开始活动了
        sub.doSomething();
    }

}