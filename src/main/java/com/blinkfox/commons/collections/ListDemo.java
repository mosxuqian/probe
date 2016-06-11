package com.blinkfox.commons.collections;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Apache Commons Collections包List相关的使用测试
 * Created by blinkfox on 16-5-29.
 */
public class ListDemo {

    /**
     * 构造list集合1
     * @return
     */
    private static List<String> buildList1() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        return list1;
    }

    /**
     * 构造list集合2
     * @return
     */
    private static List<String> buildList2() {
        List<String> list2 = new ArrayList<String>();
        list2.add("3");
        list2.add("4");
        list2.add("5");
        return list2;
    }

    /**
     * 得到两个集合中相同的元素
     */
    private static void retainAll() {
        List<String> list1 = buildList1();
        List<String> list2 = buildList2();

        // 得到两个集合中相同的元素
        Collection c = CollectionUtils.retainAll(list1, list2);
        System.out.println("c" + c);
    }

    public static void main(String[] args) {
        retainAll();
    }

}