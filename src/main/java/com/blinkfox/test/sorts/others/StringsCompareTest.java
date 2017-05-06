package com.blinkfox.test.sorts.others;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.pmw.tinylog.Logger;

/**
 * 字符串集合排序.
 * Created by blinkfox on 2017-05-06.
 */
public class StringsCompareTest {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("13801508072");
        list.add("13814990039");
        list.add("13890173043");
        list.add("13846295460");

        Logger.info("sort before list:" + list.toString());

        // 降序排列
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        Logger.info("sort after list:" + list.toString());
    }

}