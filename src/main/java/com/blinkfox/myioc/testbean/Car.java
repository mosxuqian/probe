package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.utils.Log;

/**
 * 测试的car 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider("car")
public class Car {

    private static final Log log = Log.get(Car.class);

    @Injection
    private Engine myEngine;

    @Injection
    private Door myDoor;

    @Injection
    private Wheel wheel;

    @Injection
    private Material material;

    public void start() {
        log.info("引擎是:" + myEngine.getName() + ",门的名字是:" + myDoor +
                ",材料名称:" + material.getName() + ",轮子:" + wheel.getName());
        log.info("开始开车...");
    }

}