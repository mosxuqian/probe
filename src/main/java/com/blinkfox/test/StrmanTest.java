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

        // containsAll 判断一个字符串是否包含某字符串数组中的所有元素
        boolean s9 = Strman.containsAll("foo bar", new String[]{"foo", "bar"});
        boolean s10 = Strman.containsAll("foo bar", new String[]{"FOO", "bar"}, false);
        System.out.println("containsAll:" + s9 + ", " + s10); // result => "true, true"

        // containsAny 判断一个字符串是否包含某字符串数组中的任意一个元素
        boolean s11 = Strman.containsAny("foo bar", new String[]{"FOO", "BAR", "Test"}, false);
        System.out.println("containsAny:" + s11); // result => "true"

        // countSubstr 判断一个字符串包含某字符串的个数
        long s12 = Strman.countSubstr("aaaAAAaaa", "aaa");
        long s13 = Strman.countSubstr("aaaAAAaaa", "aaa", false, false);
        System.out.println("countSubstr:" + s12 + ", " + s13); // result => "2, 3"

        // endsWith 判断一个字符串是否以某个字符串结尾
        boolean s14 = Strman.endsWith("foo bar", "bar");
        boolean s15 = Strman.endsWith("foo bar", "BAR", false);
        System.out.println("endsWith:" + s14 + ", " + s15); // result => "true, true"

        // ensureLeft 确保一个字符串以某个字符串开头,如果不是,则在前面追加该字符串,并将字符串结果返回
        String s16 = Strman.ensureLeft("foobar", "foo");
        String s17 = Strman.ensureLeft("bar", "foo");
        String s18 = Strman.ensureLeft("foobar", "FOO", false);
        System.out.println("ensureLeft:" + s16 + ", " + s17 + ", " + s18); // result => "foobar, foobar, foobar"
    }

}