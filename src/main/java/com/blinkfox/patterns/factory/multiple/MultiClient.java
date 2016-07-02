package com.blinkfox.patterns.factory.multiple;

import com.blinkfox.patterns.factory.Product;

/**
 * 多工厂方法模式客户端场景类
 * Created by blinkfox on 16-7-2.
 */
public class MultiClient {

    public static void main(String[] args) {
        Product concreteProduct1 = (new ConcreteFactory1()).createProduct();
        concreteProduct1.method1();
        concreteProduct1.method2();

        Product concreteProduct2 = (new ConcreteFactory2()).createProduct();
        concreteProduct1.method1();
        concreteProduct1.method2();
    }

}