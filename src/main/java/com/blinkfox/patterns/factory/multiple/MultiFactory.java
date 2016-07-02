package com.blinkfox.patterns.factory.multiple;

import com.blinkfox.patterns.factory.Product;

/**
 * 生成多个产品的抽象工厂类
 * Created by blinkfox on 16-7-2.
 */
public abstract class MultiFactory {

    /**
     * 生成某种产品的方法
     * @return
     */
    public abstract Product createProduct();

}