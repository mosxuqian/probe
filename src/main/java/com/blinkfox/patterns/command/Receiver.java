package com.blinkfox.patterns.command;

/**
 * 通用的抽象 Receiver 接收者
 * Created by blinkfox on 16/8/17.
 */
public abstract class Receiver {

    /**
     * 定义每个接收者都必须完成的业务
     */
    public abstract void doSomething();

}