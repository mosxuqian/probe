package com.blinkfox.patterns.chain;

/**
 * 责任连模式的客户端场景类.
 * Created by blinkfox on 16/7/11.
 */
public class ChainClient {

    /**
     * 主入口方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handlern = new ConcreteHandlerN();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handlern);

        //假设这个请求是ConcreteHandler2的责任
        handler1.handlerRequest("ConcreteHandler2");
    }

}
