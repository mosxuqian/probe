package com.blinkfox.patterns.state;

/**
 * 环境角色类
 * Created by blinkfox on 16/7/12.
 */
public class Context {

    // 当前状态
    private IState state;

    /**
     * 构造方法
     * @param state
     */
    public Context(IState state) {
        this.state = state;
    }

    /**
     * 方法1
     */
    public void handle1() {
        this.state.handle1();
    }

    /**
     * 方法2
     */
    public void handle2() {
        this.state.handle2();
    }

}