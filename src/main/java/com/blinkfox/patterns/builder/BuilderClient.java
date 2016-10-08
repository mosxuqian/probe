package com.blinkfox.patterns.builder;

/**
 * 建造者模式的客户端测试类
 * Created by blinkfox on 2016/10/8.
 */
public class BuilderClient {

    /**
     * 主入口方法
     * @param args
     */
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.getResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }

}