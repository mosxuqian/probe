package com.blinkfox.patterns.mediator;

/**
 * 中介者模式的场景类
 * Created by blinkfox on 16/8/21.
 */
public class MediatorClient {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();

        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);
        mediator.setColleague1(colleague1);
        mediator.setColleague2(colleague2);

        colleague1.depMethod1();
        colleague2.depMethod2();
        mediator.doSomething1();
        mediator.doSomething2();
    }

}