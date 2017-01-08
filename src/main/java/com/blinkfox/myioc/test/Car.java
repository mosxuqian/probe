package com.blinkfox.myioc.test;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;

/**
 * 测试的car 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider
public class Car {

    @Injection
    private Engine engine;

    @Injection
    private Door door;

    public void start() {
        System.out.println("引擎是:" + engine.getName() + ",门的名字是:" + door);
        System.out.println("开始开车...");
    }

}