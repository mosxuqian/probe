package com.blinkfox.patterns.adapter;

/**
 * 客户端场景类.
 *
 * @author blinkfox on 2018-12-11.
 */
public class Client {

    /**
     * main方法.
     *
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // 原有业务逻辑.
        Target target = new ConcreteTarget();
        target.request();

        // 增加了适配器角色后的业务逻辑.
        Target adaptTarget = new Adapter();
        adaptTarget.request();
    }

}
