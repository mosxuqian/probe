package com.blinkfox.patterns.observer;

/**
 * 具体的被观察者
 * Created by blinkfox on 16/7/15.
 */
public class ConcreteSubject extends Subject {

    /**
     * 具体的业务
     */
    public void doSomething() {
        super.notifyObservers();
    }

}