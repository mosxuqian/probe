package com.blinkfox.patterns.builder;

/**
 * 抽象的建造者
 * Created by blinkfox on 2016/10/8.
 */
public interface Builder {

    /**
     * 产品建造部分1
     */
    void buildPart1();

    /**
     * 产品建造部分2
     */
    void buildPart2();

    /**
     * 得到建造的产品
     * @return
     */
    Product getResult();

}