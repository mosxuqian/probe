package com.blinkfox.patterns.factory.normal;

import com.blinkfox.patterns.factory.Product;

/**
 * 抽象的工厂类
 * Created by blinkfox on 16-6-29.
 */
public abstract class Factory {

    /**
     * 运用了Java中的泛型和反射技术,生成某种具体的产品
     * 其输入类型可以自行设置
     * @param c
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> c);

}