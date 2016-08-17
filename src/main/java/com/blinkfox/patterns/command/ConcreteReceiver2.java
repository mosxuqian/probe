package com.blinkfox.patterns.command;

/**
 * 具体的 Receiver 类2
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteReceiver2 extends Receiver {

    @Override
    public void doSomething() {
        System.out.println("ConcreteReceiver2 处理的业务逻辑...");
    }

}