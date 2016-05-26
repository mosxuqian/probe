package com.blinkfox.commons.collections;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.LinkedMap;
import java.util.Map;

/**
 * Apache Commons Collections包map相关的使用测试
 * Created by blinkfox on 16-5-26.
 */
public class MapDemo {

    /**
     * 构建map初始数据
     *
     * @param map
     */
    private static void buildMap(Map map) {
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
    }

    /**
     * 遍历map
     *
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
     * 遍历map测试
     */
    private static void iterableMapTest() {
        IterableMap iterMap = new HashedMap<>();
        buildMap(iterMap);
        loopMap(iterMap, "iterableMap");
    }

    /**
     * 得到集合里按顺序存放的key之后的某一Key
     */
    private static void linkedMapTest() {
        OrderedMap<String, Object> orderMap = new LinkedMap<String, Object>();
        buildMap(orderMap);

        // 获取map中相应的值
        System.out.println("LinkedMap firstKey:" + orderMap.firstKey());
        System.out.println("LinkedMap previous key:" + orderMap.previousKey("four"));
        System.out.println("LinkedMap next key:" + orderMap.nextKey("two"));
        System.out.println("LinkedMap last key:" + orderMap.lastKey());
        System.out.println("LinkedMap map Size:" + orderMap.size());
    }

    /**
     * BidiMap Demo
     * 通过key得到value
     * 通过value得到key
     * 将map里的key和value对调
     */
    private static void bidiMapTest() {
        BidiMap bidiMap = new TreeBidiMap();
        buildMap(bidiMap);
        bidiMap.put("san", "3");    // 有相同值的，只有最后一个生效
        loopMap(bidiMap, "BidiMap");

        // 获取map中相应的值
        System.out.println("BidiMap getKey:" + bidiMap.getKey("2"));
        System.out.println("BidiMap getMoreSameKey:" + bidiMap.getKey("3"));

        bidiMap.removeValue("3");
        System.out.println("BidiMap getMoreSameKey2:" + bidiMap.getKey("3"));
        BidiMap inversMap = bidiMap.inverseBidiMap();
        loopMap(inversMap, "inversMap");
    }

    public static void main(String[] args) {
        iterableMapTest();
        linkedMapTest();
        bidiMapTest();
    }

}