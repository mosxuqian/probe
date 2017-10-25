package com.blinkfox.patterns.chain;

/**
 * 具体处理角色2.
 * Created by blinkfox on 16/7/11.
 */
public class ConcreteHandler2 extends Handler {

    /**
     * 具体处理角色2的处理方法.
     * @param condition 条件
     */
    @Override
    public void handlerRequest(String condition) {
        // 如果是自己的责任，就自己处理，负责传给下家处理
        if ("ConcreteHandler2".equals(condition)) {
            System.out.println( "具体处理角色2的处理方法handled1...");
        } else {
            System.out.println( "具体处理角色2 通过...");
            getNextHandler().handlerRequest(condition);
        }
    }

}