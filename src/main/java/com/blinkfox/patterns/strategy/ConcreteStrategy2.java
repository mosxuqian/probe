package com.blinkfox.patterns.strategy;

/**
 * 具体策略类2
 * Created by blinkfox on 16-6-21.
 */
public class ConcreteStrategy2 implements IStrategy {

    @Override
    public void doSomething() {
        System.out.println("具体策略的策略方法2");
    }

}