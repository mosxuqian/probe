package com.blinkfox.patterns.mediator;

/**
 * 抽象的同事类
 * Created by blinkfox on 16/8/21.
 */
public abstract class Colleague {

    // 中介者
    protected Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

}