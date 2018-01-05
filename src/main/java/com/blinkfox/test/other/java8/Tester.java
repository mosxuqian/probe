package com.blinkfox.test.other.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * Tester.
 *
 * @author blinkfox on 2018-01-05.
 */
public class Tester {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        list.forEach(System.out::println);
    }

}