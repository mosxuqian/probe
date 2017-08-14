package com.blinkfox.learn.algorithm;

import org.pmw.tinylog.Logger;

/**
 * 二分查找法实现.
 * @author blinkfox on 2017-08-14.
 */
public class BinarySearch {

    /**
     * 从数组中查找目标元素所在的索引位置.
     * @param arr 待查找的数组
     * @param target 目标元素
     * @return 索引位置
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        // 通过循环实现.
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * main方法.
     * @param args 数组参数.
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 8, 13, 26};
        int index = binarySearch(arr, 8);
        Logger.info("索引数：{}", index);
    }

}