package com.blinkfox.patterns.chain;

/**
 * 责任连模式的抽象处理者角色.
 * Created by blinkfox on 16/7/11.
 */
public abstract class Handler {

    /** 下一个处理者角色. */
    private Handler nextHandler;

    /**
     * 处理请求的抽象方法.
     * @param condition 条件
     */
    public abstract void handlerRequest(String condition);

    /* getter和setter方法 */

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

}