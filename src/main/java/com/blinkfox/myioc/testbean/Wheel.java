package com.blinkfox.myioc.testbean;

import com.blinkfox.myioc.annotation.Provider;

/**
 * 制造汽车需要的轮子 类
 * Created by blinkfox on 2017/1/8.
 */
@Provider
public class Wheel {

    private String name; // 名称

    private double radius; // 半径

    /* getter 和 setter 方法*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}