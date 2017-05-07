package com.blinkfox.test.sorts.others;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.pmw.tinylog.Logger;

/**
 * 字符串集合排序.
 * Created by blinkfox on 2017-05-06.
 */
public class LongsCompareTest {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        List<Long> list = new ArrayList<Long>();
        list.add(13801508072L);
        list.add(13814990039L);
        list.add(13890173043L);
        list.add(13846295460L);

        Logger.info("sort before list:" + list.toString());

        // 降序排序
        Collections.sort(list, new LongComparator());
        /*Collections.sort(list, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o2.compareTo(o1);
            }
        });*/
        // 降序排列

        Logger.info("sort after list:" + list.toString());
    }

}