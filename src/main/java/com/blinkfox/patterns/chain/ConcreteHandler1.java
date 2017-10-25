package com.blinkfox.patterns.chain;

/**
 * 具体处理角色1.
 * Created by blinkfox on 16/7/11.
 */
public class ConcreteHandler1 extends Handler {

    /**
     * 具体处理角色1的处理方法.
     * @param condition 条件
     */
    @Override
    public void handle(String condition) {
        // 如果是自己的责任，就自己处理，负责传给下家处理
        if ("ConcreteHandler1".equals(condition)) {
            System.out.println( "具体处理角色1的处理方法handled1...");
        } else {
            System.out.println( "具体处理角色1 通过...");
            nextHandler.handle(condition);
        }
    }

}