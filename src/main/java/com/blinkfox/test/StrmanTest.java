package com.blinkfox.test;

import strman.Strman;
import java.util.Arrays;
import java.util.Optional;

/**
 * strman-java包的测试使用类
 * Created by blinkfox on 16/7/17.
 */
public class StrmanTest {

    public static void main(String[] args) {
        // append 在一个字符串后追加任意个数的字符串
        String s1 = Strman.append("f", "o", "o", "b", "a", "r");
        System.out.println("append:" + s1); // result => "foobar"

        // appendArray 在一个字符串后先后追加一个字符串数组中的元素
        String s2 = Strman.appendArray("f", new String[]{"o", "o", "b", "a", "r"});
        System.out.println("append:" + s2); //  result => "foobar"

        // at 根据字符串的索引获取到对应的字符。如果索引是负数,则逆向获取,超出则抛出异常
        Optional<String> s3 = Strman.at("foobar", 3);
        System.out.println("at:" + s3.get()); // result => "b"

        // between 得到一个字符串中,开始字符串和结束字符串之间的字符串的数组
        String[] s4 = Strman.between("[abc], [def]", "[", "]");
        System.out.println("between:" + Arrays.toString(s4)); // result => "[abc, def]"

        // chars 得到一个字符串中所有字符构成的字符串数组
        String[] s5 = Strman.chars("title");
        System.out.println("chars:" + Arrays.toString(s5)); // result => "[t, i, t, l, e]"

        // collapseWhitespace 替换掉连续的多个空格为一个空格
        String s6 = Strman.collapseWhitespace("foo    bar");
        System.out.println("chars:" + s6); // result => "foo bar"

        // contains 判断一个字符串是否包含另外一个字符串,第三个参数,表示字符串大小写是否敏感
        boolean s7 = Strman.contains("foo bar", "foo");
        boolean s8 = Strman.contains("foo bar", "FOO", false);
        System.out.println("contains:" + s7 + ", " + s8); // result => "true, true"
    }

}