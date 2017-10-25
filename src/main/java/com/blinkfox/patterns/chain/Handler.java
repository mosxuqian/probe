package com.blinkfox.patterns.chain;

/**
 * 责任连模式的抽象处理者角色.
 * Created by blinkfox on 16/7/11.
 */
public abstract class Handler {

    /** 后继处理者角色. */
    protected Handler nextHandler;

    /**
     * 处理请求的抽象方法.
     * @param condition 条件
     */
    public abstract void handle(String condition);

    /**
     * nextHandler的Setter方法.
     * @param nextHandler 后继处理器
     */
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

}