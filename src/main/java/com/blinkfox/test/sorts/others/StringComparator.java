package com.blinkfox.test.sorts.others;

import java.util.Comparator;

/**
 * 单例的StringComparator.
 * Created by blinkfox on 2017/5/7.
 */
public class StringComparator implements Comparator<String> {

    /* 初始化不可变实例 */
    private static final StringComparator comparator = new StringComparator();

    /**
     * 私有构造方法，防止 new 新的实例.
     */
    private StringComparator() {
        super();
    }

    /**
     * 获取唯一实例.
     * @return StringComparator实例
     */
    public static StringComparator getInstance() {
        return comparator;
    }

    /**
     * 倒序排列.
     * @param o1 字符串1
     * @param o2 字符串2
     * @return index
     */
    @Override
    public int compare(String o1, String o2) {
        return o2.compareTo(o1);
    }

}