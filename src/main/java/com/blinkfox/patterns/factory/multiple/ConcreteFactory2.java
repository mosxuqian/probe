package com.blinkfox.patterns.factory.multiple;

import com.blinkfox.patterns.factory.ConcreteProduct2;
import com.blinkfox.patterns.factory.Product;

/**
 * 生成产品2的具体工厂类2
 * Created by blinkfox on 16-7-2.
 */
public class ConcreteFactory2 extends MultiFactory {

    /**
     * 生成产品2的方法
     * @return
     */
    @Override
    public Product createProduct() {
        return new ConcreteProduct2();
    }

}