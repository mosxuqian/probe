package com.blinkfox.test.sorts.others;

import java.util.Comparator;

/**
 * Long型数据倒序比较器
 * Created by blinkfox on 2017/5/7.
 */
public class LongComparator implements Comparator<Long> {

    /* 初始化不可变实例 */
    private static final LongComparator comparator = new LongComparator();

    /**
     * 私有构造方法，防止 new 新的实例.
     */
    private LongComparator() {
        super();
    }

    /**
     * 获取唯一实例.
     * @return Comparator实例
     */
    public static LongComparator getInstance() {
        return comparator;
    }

    @Override
    public int compare(Long o1, Long o2) {
        return o2.compareTo(o1);
    }

}