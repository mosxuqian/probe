package com.blinkfox.patterns.factory.lazy;

import com.blinkfox.patterns.factory.Product;

/**
 * 延迟加载的工厂模式客户端场景类
 * Created by blinkfox on 16-7-4.
 */
public class LazyClient {

    public static void main(String[] args) {
        Product product1 = LazyFactory.createProduct("product1");

        Product product11 = LazyFactory.createProduct("product1");
    }

}