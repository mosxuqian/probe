package com.blinkfox.patterns.factory.normal;

import com.blinkfox.patterns.factory.ConcreteProduct1;
import com.blinkfox.patterns.factory.ConcreteProduct2;
import com.blinkfox.patterns.factory.Product;

/**
 * 工厂方法模式客户端场景类
 * Created by blinkfox on 16-6-29.
 */
public class Client {

    public static void main(String[] args) {
        Factory factory = new ConcreteFactory();
        Product product1 = factory.createProduct(ConcreteProduct1.class);
        product1.method1();
        product1.method2();

        Product product2 = factory.createProduct(ConcreteProduct2.class);
        product2.method1();
        product2.method2();
    }

}