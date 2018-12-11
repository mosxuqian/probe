package com.blinkfox.patterns.adapter;

/**
 * 适配者类.
 *
 * @author blinkfox on 2018-12-11.
 */
public class Adaptee {

    /**
     * 这是原有的业务逻辑方法.
     */
    public void doSomething() {
        System.out.println("Hello, I'm Adaptee method.");
    }

}
