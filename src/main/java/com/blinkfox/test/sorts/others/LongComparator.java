package com.blinkfox.test.sorts.others;

import java.util.Comparator;

/**
 * Long型数据倒序比较器
 * Created by blinkfox on 2017/5/7.
 */
public class LongComparator implements Comparator<Long> {

    @Override
    public int compare(Long o1, Long o2) {
        return o2.compareTo(o1);
    }

}