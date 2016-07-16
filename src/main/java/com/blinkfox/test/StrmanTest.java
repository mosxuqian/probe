package com.blinkfox.test;

import strman.Strman;

import java.util.Optional;

/**
 * strman-java包的测试使用类
 * Created by blinkfox on 16/7/17.
 */
public class StrmanTest {

    public static void main(String[] args) {
        String s1 = Strman.append("f", "o", "o", "b", "a", "r");
        System.out.println("append_s1:" + s1);

        String s2 = Strman.appendArray("f", new String[]{"o", "o", "b", "a", "r"});
        System.out.println("append_s2:" + s2);

        Optional<String> o1 = Strman.at("foobar", 0);
        System.out.println("at_o1:" + o1.get());
    }

}