package com.blinkfox.test.sorts.quick;

import java.util.Arrays;
import org.pmw.tinylog.Logger;

/**
 * 快速排序示例.
 * Created by blinkfox on 2017/5/2.
 */
public class QuickSortTest {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        int[] arr = {9, 8, -3, 6, -1, 5, 4, 3, 2, 7, 1, 0, -2};
        Logger.info("排序之前:{}", Arrays.toString(arr));

        QuickSortUtils.sort(arr);

        Logger.info("排序之后:{}", Arrays.toString(arr));
    }

}