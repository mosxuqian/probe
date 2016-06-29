package com.blinkfox.patterns.factory.normal;

/**
 * 客户端场景类
 * Created by blinkfox on 16-6-29.
 */
public class Client {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product = factory.createProduct(ConcreteProduct1.class);
    }

}