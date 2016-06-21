package com.blinkfox.patterns.strategy;

/**
 * 封装策略角色的环境类
 * Created by blinkfox on 16-6-21.
 */
public class Context {

    // 抽象策略
    private IStrategy strategy;

    /**
     * 构造函数设置具体策略
     * @param strategy
     */
    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 封装后的策略方法
     */
    public void doAnything() {
        this.strategy.doSomething();
    }

}