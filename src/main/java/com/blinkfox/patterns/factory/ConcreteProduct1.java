package com.blinkfox.patterns.factory;

import com.blinkfox.patterns.factory.Product;

/**
 * 具体产品类1
 * Created by blinkfox on 16-6-29.
 */
public class ConcreteProduct1 extends Product {

    @Override
    public void method2() {
        System.out.println("ConcreteProduct1的method2方法");
    }

}