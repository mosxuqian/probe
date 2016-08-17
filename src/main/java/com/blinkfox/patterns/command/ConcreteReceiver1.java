package com.blinkfox.patterns.command;

/**
 * 具体的 Receiver 类1
 * Created by blinkfox on 16/8/17.
 */
public class ConcreteReceiver1 extends Receiver {

    @Override
    public void doSomething() {
        System.out.println("ConcreteReceiver1 处理的业务逻辑...");
    }

}