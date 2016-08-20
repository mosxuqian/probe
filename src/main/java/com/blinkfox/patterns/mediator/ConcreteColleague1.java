package com.blinkfox.patterns.mediator;

/**
 * 具体的同事类1
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    /**
     * 自有方法
     */
    public void selfMethod1() {
        System.out.println("------ConcreteColleague1-处理自己的业务逻辑1--------");
    }

    /**
     * 依赖方法
     */
    public void depMethod1() {
        System.out.println("------ConcreteColleague1-委托给中介者的业务逻辑1--------");
        super.mediator.doSomething1();
    }

}