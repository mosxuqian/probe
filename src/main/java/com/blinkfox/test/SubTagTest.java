package com.blinkfox.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 截取并得到标签之间的字符串
 * Created by blinkfox on 2016/10/30.
 */
public class SubTagTest {

    public static void main(String[] args) {
        // String testStr = "abc<icon>def</icon>deftfh<icon>a</icon>";
        String testStr = "abc#{bookName}deftfh#{money}";
        List<String> strs = match(testStr, "#\\{", "\\}");
        for (String s: strs) {
            System.out.println("---匹配到的字符串有:" + s);
        }
    }

    /**
     * 获取给定字符串和前缀、后缀截取后的字符串集合
     * @param s
     * @param startStr
     * @param endStr
     * @return
     */
    public static List<String> match(String s, String startStr, String endStr) {
        List<String> results = new ArrayList<String>();
        String reg = startStr + "(.*?)"+ endStr;
        System.out.println("------reg：" + reg);
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(s);
        while (!m.hitEnd() && m.find()) {
            results.add(m.group(1));
        }
        return results;
    }

}