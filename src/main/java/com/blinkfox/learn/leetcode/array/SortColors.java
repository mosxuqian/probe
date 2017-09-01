package com.blinkfox.learn.leetcode.array;

import java.util.Arrays;

/**
 * 对数组中相同颜色进行排序.
 * @author blinkfox on 2017-09-01.
 */
public class SortColors {

    /**
     * 通过计数的方式进行排序的方法.
     * @param nums 数组
     * @return 数组
     */
    private static int[] sortColors(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) < 2) {
            return nums;
        }

        // 先对`0, 1, 2`颜色分别进行计数.
        int[] counts = new int[3];
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            counts[num]++;
        }

        // 数组元素各位置分别置为`0, 1, 2`
        len = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < counts[i]; j++) {
                nums[len++] = i;
            }
        }
        return nums;
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        int[] arr = {2, 1, 1, 0, 2, 0, 1, 2, 1, 0, 2, 0, 1};
        System.out.println("排序前的数组元素:" + Arrays.toString(arr));
        System.out.println("排序后的数组元素:" + Arrays.toString(sortColors(arr)));
    }

}