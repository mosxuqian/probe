package com.blinkfox.patterns.factory.simple;

import com.blinkfox.patterns.factory.ConcreteProduct1;
import com.blinkfox.patterns.factory.ConcreteProduct2;
import com.blinkfox.patterns.factory.Product;

/**
 * 简单工厂模式客户端场景类
 * Created by blinkfox on 16-6-29.
 */
public class SimpleClient {

    public static void main(String[] args) {
        Product product1 = SimpleFactory.createProduct(ConcreteProduct1.class);
        product1.method1();
        product1.method2();

        Product product2 = SimpleFactory.createProduct(ConcreteProduct2.class);
        product2.method1();
        product2.method2();
    }

}