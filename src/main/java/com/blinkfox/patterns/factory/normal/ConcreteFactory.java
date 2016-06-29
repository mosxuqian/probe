package com.blinkfox.patterns.factory.normal;

import com.blinkfox.patterns.factory.Product;

/**
 * 具体生产产品的工厂类
 * Created by blinkfox on 16-6-29.
 */
public class ConcreteFactory extends Factory {

    /**
     * /**
     * 运用了Java中的泛型和反射技术,生成某种具体的产品
     * 其输入类型可以自行设置
     * @param c
     * @param <T>
     * @return
     */
    @Override
    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }

}
