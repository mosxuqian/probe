package com.blinkfox.patterns.adapter;

/**
 * 具体的目标角色实现类.
 *
 * @author blinkfox on 2018-12-11.
 */
public class ConcreteTarget implements Target {

    /**
     * 目标角色自己的方法.
     */
    @Override
    public void request() {
        System.out.println("hello, I'm concrete target method.");
    }

}
