package com.blinkfox.patterns.state;

/**
 * 具体状态角色类2
 * Created by blinkfox on 16/7/12.
 */
public class ConcreteState2 implements IState {

    /**
     * 具体状态角色类2中的方法1
     */
    @Override
    public void handle1() {
        System.out.println("执行了具体状态角色类2中的方法1...");
    }

    /**
     * 具体状态角色类2中的方法2
     */
    @Override
    public void handle2() {
        System.out.println("执行了具体状态角色类2中的方法2...");
    }
}
