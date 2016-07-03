package com.blinkfox.patterns.factory.lazy;

import com.blinkfox.patterns.factory.ConcreteProduct1;
import com.blinkfox.patterns.factory.ConcreteProduct2;
import com.blinkfox.patterns.factory.Product;
import java.util.HashMap;
import java.util.Map;

/**
 * 延迟加载的工厂类
 * Created by blinkfox on 16-7-4.
 */
public class LazyFactory {

    private static final Map<String, Product> lazyMap = new HashMap<String, Product>();

    public static synchronized Product createProduct(String type) {
        Product product = null;

        // 如果map中已经有这个对象，则直接取出该对象即可，否则创建并放在缓存容器中
        if (lazyMap.containsKey(type)) {
            System.out.println(type + "已存在,直接从缓存中获取");
            return lazyMap.get(type);
        }

        // 根据类型创建具体的产品对象
        if ("product1".equals(type)) {
            product = new ConcreteProduct1();
        } else {
            product = new ConcreteProduct2();
        }
        System.out.println(type + "不存在,创建并放在缓存中");

        // 同时把对象放到缓存容器中
        lazyMap.put(type, product);

        return product;
    }

}