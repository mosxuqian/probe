package com.blinkfox.test.sorts.quick;

/**
 * 数组工具类.
 * Created by blinkfox on 2017/5/2.
 */
public final class ArrayUtils {

    /**
     * 私有构造方法.
     */
    private ArrayUtils() {
        super();
    }

    /**
     * 打印数组信息.
     * @param array 数组
     */
    public static void printArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }



}