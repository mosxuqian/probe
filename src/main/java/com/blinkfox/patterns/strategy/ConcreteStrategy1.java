package com.blinkfox.patterns.strategy;

/**
 * 具体策略类1
 * Created by blinkfox on 16-6-21.
 */
public class ConcreteStrategy1 implements IStrategy {

    @Override
    public void doSomething() {
        System.out.println("具体策略的策略方法1");
    }

}