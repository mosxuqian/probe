package com.blinkfox.learn.commons.collections;

import org.apache.commons.collections.*;
import org.apache.commons.collections.bidimap.TreeBidiMap;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiValueMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Apache Commons Collections包map相关的使用测试
 * Created by blinkfox on 16-5-26.
 */
public class MapDemo {

    /**
     * 构建map初始数据
     * @param map
     */
    private static void buildMap(Map map) {
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
    }

    /**
     * map迭代器
     * 遍历打印map,使用map.mapIterator()
     * @param map
     * @param mapName
     */
    private static void loopMap(IterableMap map, String mapName) {
        System.out.println("---------------------------");
        // 遍历map
        MapIterator it = map.mapIterator();
        while (it.hasNext()) {
            Object key = it.next();
            Object value = it.getValue();
            System.out.println(mapName + " key:" + key + ", value: " + value);
        }
        System.out.println("---------------------------");
    }

    /**
     * 按顺序存放的LinkedMap
     */
    private static void linkedMapTest() {
        OrderedMap orderMap = new LinkedMap();
        buildMap(orderMap);

        // 获取map中相应的值
        System.out.println("LinkedMap firstKey:" + orderMap.firstKey());
        System.out.println("LinkedMap previous key:" + orderMap.previousKey("four"));
        System.out.println("LinkedMap next key:" + orderMap.nextKey("two"));
        System.out.println("LinkedMap last key:" + orderMap.lastKey());
        System.out.println("LinkedMap map Size:" + orderMap.size());
    }

    /**
     * BidiMap,是双向Map
     * 通过key得到value
     * 通过value得到key
     * 注意的是BidiMap,当中不光key不能重复，value也不可以。
     */
    private static void bidiMapTest() {
        BidiMap bidiMap = new TreeBidiMap();
        buildMap(bidiMap);
        // 有相同值的，只有最后一个生效
        bidiMap.put("san", "3");
        loopMap(bidiMap, "BidiMap");

        // 获取map中相应的值
        System.out.println("BidiMap getKey:" + bidiMap.getKey("2"));
        System.out.println("BidiMap getMoreSameKey:" + bidiMap.getKey("3"));

        // 移除map的value
        bidiMap.removeValue("3");
        System.out.println("BidiMap getMoreSameKey2:" + bidiMap.getKey("3"));

        // 交换map的key和value
        BidiMap inversMap = bidiMap.inverseBidiMap();
        loopMap(inversMap, "inversMap");
    }

    /**
     * MultiMap 单个key指向多个对象,就是单个key可以对应多个value,
     * 在put或remove时和普通Map没有区别,但当get时将返回多个value,
     * 所以返回一个collections,利用MultiMap，我们就可以很方便的往 一个
     * key上放数量不定的对象，也就实现了一对多,在3.2.1版本中MultiHashMap已被废除,
     * 请使用MultiValueMap
    */
    private static void multiMapTest() {
        MultiMap multiMap = new MultiValueMap();
        buildMap(multiMap);
        multiMap.put("three", "5");
        List<String> list = (List<String>) multiMap.get("three");
        // 打印: list:[3, 5]
        System.out.println("list:" + list);
    }

    /**
     * LazyMap.类似与Hibenrate的懒加载,在声明的时候并不会创建,
     * 而是在使用(get)的时候,才创建集合的内容,返回Factory的返回值
     * 实现懒加载,当我们觉得没有必要去初始化一个Map而又希望它可以在必要时自动处理数据可以使用LazyMap
     * 有LazyList与LazyMap对应
     */
    private static void lazyMapTest() {
        //创建一个工厂，实现create方法
        Factory factory = new Factory() {
            @Override
            public Object create() {
                // 创建的默认值
                return "这是LazyMap get()不到时创建的默认值";
            }
        };

        Map lazyMap = LazyMap.decorate(new HashMap(), factory);
        System.out.println("map:" + lazyMap);
        //当此lazyMap调用get(key)时，如果无此key则返回varFactory里create方法返回的值
        System.out.println("map:" + lazyMap.get("hello"));

        // 有key对应的值时,返回123
        lazyMap.put("hello", "123");
        System.out.println("map:" + lazyMap.get("hello"));
    }

    public static void main(String[] args) {
        linkedMapTest();
        bidiMapTest();
        multiMapTest();
        lazyMapTest();
    }

}