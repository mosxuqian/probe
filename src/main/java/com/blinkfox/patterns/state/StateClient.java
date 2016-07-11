package com.blinkfox.patterns.state;

/**
 * 状态模式的客户端场景累
 * Created by blinkfox on 16/7/12.
 */
public class StateClient {

    public static void main(String[] args) {
        Context context = new Context(new ConcreteState1());
        context.handle1();
        context.handle2();
    }

}