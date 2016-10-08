package com.blinkfox.patterns.builder;

/**
 * 具体的建造者实现类
 * Created by blinkfox on 2016/10/8.
 */
public class ConcreteBuilder implements Builder {

    private Product product = new Product();

    /**
     * 产品建造部分1
     */
    @Override
    public void buildPart1() {
        product.setPart1("编号：95757");
    }

    /**
     * 产品建造部分2
     */
    @Override
    public void buildPart2() {
        product.setPart2("名称：小机器人");
    }

    /**
     * 得到建造的产品
     * @return
     */
    @Override
    public Product getResult() {
        return product;
    }

}