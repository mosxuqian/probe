package com.blinkfox.patterns.factory.multiple;

import com.blinkfox.patterns.factory.ConcreteProduct1;
import com.blinkfox.patterns.factory.Product;

/**
 * 生成产品1的具体工厂类1
 * Created by blinkfox on 16-7-2.
 */
public class ConcreteFactory1 extends MultiFactory {

    /**
     * 生成产品1的方法
     * @return
     */
    @Override
    public Product createProduct() {
        return new ConcreteProduct1();
    }

}