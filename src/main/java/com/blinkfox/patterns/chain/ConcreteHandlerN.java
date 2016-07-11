package com.blinkfox.patterns.chain;

/**
 * 具体处理角色2
 * Created by blinkfox on 16/7/11.
 */
public class ConcreteHandlerN extends Handler {

    /**
     * 这里假设n是链的最后一个节点必须处理掉
     * 在实际情况下，可能出现环，或者是树形，这里并不一定是最后一个节点。
     * @param condition 参数条件
     */
    @Override
    public void handlerRequest(String condition) {
        System.out.println( "具体处理角色2的处理方法 结束...");
    }

}