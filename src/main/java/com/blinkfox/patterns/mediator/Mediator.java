package com.blinkfox.patterns.mediator;

/**
 * 通用抽象中介者类
 * Created by blinkfox on 16/8/21.
 */
public abstract class Mediator {

    // 定义同事类1
    protected ConcreteColleague1 colleague1;

    // 定义同事类2
    protected ConcreteColleague2 colleague2;

    /* getter 和 setter 方法 */
    public ConcreteColleague1 getColleague1() {
        return colleague1;
    }

    public void setColleague1(ConcreteColleague1 colleague1) {
        this.colleague1 = colleague1;
    }

    public ConcreteColleague2 getColleague2() {
        return colleague2;
    }

    public void setColleague2(ConcreteColleague2 colleague2) {
        this.colleague2 = colleague2;
    }

    /**
     * 中介者模式的抽象业务逻辑1
     */
    public abstract void doSomething1();

    /**
     * 中介者模式的抽象业务逻辑2
     */
    public abstract void doSomething2();

}