package com.blinkfox.patterns.mediator;

/**
 * 具体的同事类2
 * Created by blinkfox on 16/8/21.
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    /**
     * 自有方法2
     */
    public void selfMethod2() {
        System.out.println("------ConcreteColleague2-处理自己的业务逻辑2--------");
    }

    /**
     * 依赖方法2
     */
    public void depMethod2() {
        System.out.println("------ConcreteColleague2-委托给中介者的业务逻辑2--------");
        super.mediator.doSomething2();
    }

}