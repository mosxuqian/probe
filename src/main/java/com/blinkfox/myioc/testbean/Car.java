package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;

/**
 * 测试的car 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider("car")
public class Car {

    @Injection
    private Engine engine;

    @Injection
    private Door door;

    @Injection
    private Wheel wheel;

    @Injection
    private Material material;

    public void start() {
        System.out.println("引擎是:" + engine.getName() + ",门的名字是:" + door +
                ",材料名称:" + material.getName() + ",轮子:" + wheel.getName());
        System.out.println("开始开车...");
    }

}