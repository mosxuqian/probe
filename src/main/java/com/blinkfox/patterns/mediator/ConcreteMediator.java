package com.blinkfox.patterns.mediator;

/**
 * 具体的通用中介者类
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteMediator extends Mediator {

    /**
     * 中介者模式的具体业务逻辑1
     */
    @Override
    public void doSomething1() {
        super.colleague1.selfMethod1();
        super.colleague2.selfMethod2();
    }

    /**
     * 中介者模式的具体业务逻辑2
     */
    @Override
    public void doSomething2() {
        super.colleague1.selfMethod1();
        super.colleague2.selfMethod2();
    }

}