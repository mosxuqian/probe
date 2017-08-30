package com.blinkfox.learn.leetcode.array;

import java.util.Arrays;

/**
 * LeetCode中的第283道问题，移动int数组中的`0`元素到数组末尾.
 * @author blinkfox on 2017-08-30.
 */
public class MoveZeroes {

    /**
     * 通过先将非`0`元素置于到数组前面，后面遍历补0的方式来移动int数组中的`0`元素到数组末尾的方法.
     * @param nums 原数组
     */
    public static int[] moveZeroes(int[] nums) {
        int len = 0;
        if (nums == null || (len = nums.length) < 2) {
            return nums;
        }

        // 将数组非零元素按原顺序排列出来.
        int notZeroLen = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (num != 0) {
                nums[notZeroLen++] = num;
            }
        }

        // 最后若干位补零.
        for (int i = notZeroLen; i < len; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    /**
     * 通过将非`0`元素和非`0`元素进行交换的方式，来移动int数组中的`0`元素到数组末尾的方法.
     * @param nums 原数组
     */
    public static int[] moveZeroes2(int[] nums) {
        int len = 0;
        if (nums == null || (len = nums.length) < 2) {
            return nums;
        }

        // 遍历数组元素将非`0`元素和`0`元素进行交换
        int j = 0;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (num != 0) {
                if (i != j) {
                    nums[j] = num;
                    nums[i] = 0;
                }
                j++;
            }
        }

        return nums;
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        int[] arr = {3, 0, 0, 0, 2, 0, 1, 2, 5, 0, 7, 0, 12};
        System.out.println("移位前的数组元素:" + Arrays.toString(arr));
        // System.out.println("移位前的数组元素:" + Arrays.toString(moveZeroes(arr)));
        System.out.println("移位前的数组元素:" + Arrays.toString(moveZeroes2(arr)));
    }

}