package com.blinkfox.test.sorts.quick;

/**
 * 快速排序的工具类.
 * Created by blinkfox on 2017/5/2.
 */
public final class QuickSortUtils {

    /**
     * 私有构造方法.
     */
    private QuickSortUtils() {
        super();
    }

    /**
     * 快速排序.
     * @param array 数组
     */
    public static void sort(int[] array) {
        subQuickSort(array, 0, array.length - 1);
    }

    /**
     * 单个快排.
     * @param array 数组
     * @param start 开始下标
     * @param end 结束下标
     */
    private static void subQuickSort(int[] array, int start, int end) {
        if (array == null || (end - start + 1) < 2) {
            return;
        }

        int part = partition(array, start, end);

        if (part == start) {
            subQuickSort(array, part + 1, end);
        } else if (part == end) {
            subQuickSort(array, start, part - 1);
        } else {
            subQuickSort(array, start, part - 1);
            subQuickSort(array, part + 1, end);
        }
    }

    /**
     * 划分数组.
     * @param array 数组
     * @param start 开始下标
     * @param end 结束下标
     * @return 下标
     */
    private static int partition(int[] array, int start, int end) {
        int value = array[end];
        int index = start - 1;

        for (int i = start; i < end; i++) {
            if (array[i] < value) {
                index++;
                if (index != i) {
                    exchangeElements(array, index, i);
                }
            }
        }

        if ((index + 1) != end) {
            exchangeElements(array, index + 1, end);
        }

        return index + 1;
    }

    /**
     * 交换元素.
     * @param array 数组
     * @param index1 下标1
     * @param index2 下标2
     */
    private static void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

}