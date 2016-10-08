package com.blinkfox.patterns.builder;

/**
 * 导演者类
 * Created by blinkfox on 2016/10/8.
 */
public class Director {

    // 当前需要的建造者对象
    private Builder builder;

    /**
     * 构造方法
     * @param builder
     */
    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法，负责调用各个零件建造方法
     */
    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }

}