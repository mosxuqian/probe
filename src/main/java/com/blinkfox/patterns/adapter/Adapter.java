package com.blinkfox.patterns.adapter;

/**
 * 适配器类.
 *
 * @author blinkfox on 2018-12-11.
 */
public class Adapter extends Adaptee implements Target {

    /**
     * 适配了目标角色自己的方法.
     */
    @Override
    public void request() {
        super.doSomething();
        System.out.println("适配器适配了目标角色方法.");
    }

}
