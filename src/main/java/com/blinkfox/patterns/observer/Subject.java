package com.blinkfox.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者的抽象类
 * Created by blinkfox on 16/7/14.
 */
public abstract class Subject {

    // 定义一个观察者的集合
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * 增加一个观察者
     * @param o
     */
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    /**
     * 删除一个观察者
     * @param o
     */
    public void delObserver(Observer o) {
        this.observers.remove(o);
    }

    /**
     * 通知所有观察者
     */
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update();
        }
    }

}