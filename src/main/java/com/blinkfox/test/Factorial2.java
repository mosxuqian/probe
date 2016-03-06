package com.blinkfox.test;

/**
 * 计算正整数的阶乘(n!)各个位数数字之和
 * Created by blinkfox on 2016-03-06.
 */
public class Factorial2 {

    public static void main(String[] args) {
       int n = Integer.parseInt(args[0]);
        // int n = 50000;
        long startTime = System.currentTimeMillis();

        //计算最终结果的方法
        String digitSum = calcArrDigitSum(n);

        System.out.println("陈家银:" + (System.currentTimeMillis() - startTime) + ":" + digitSum);
    }

    /**
     * 计算阶乘各位数和的字符串
     * @param n
     * @return
     */
    private static String calcArrDigitSum(int n) {
        int[] arr = new int[n * 5];
        int arrLen = 1;
        arrLen = calcFactArr(arrLen, arr, n);
        //
        int sum[] = new int[1000];
        int sumLen = 1;
        for(int i = arrLen - 1; i >= 0; i--) {
            sum[0] += arr[i];
            // 处理进位
            sumLen = handleCarry(sumLen, sum);
        }

        //得到各位数字之和
        StringBuilder sb = new StringBuilder();
        for(int i = sumLen - 1; i >= 0; i--) {
            sb.append(sum[i]);
        }
        return sb.toString();
    }

    /**
     * 计算出阶乘对应的数组和阶乘值的长度
     * @param arrLen
     * @param arr
     * @param n
     * @return
     */
    private static int calcFactArr(int arrLen, int[] arr, int n) {
        arr[0] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < arrLen; j++) {
                arr[j] *= i;
            }

            // 处理进位
            arrLen = handleCarry(arrLen, arr);
        }
        return arrLen;
    }

    /**
     * 处理进位
     * @param arrLen
     * @param arr
     * @return
     */
    private static int handleCarry(int arrLen, int[] arr) {
        for(int j = 0; j < arrLen; ++j) {
            if(arr[j] > 9) {
                if((arrLen == (j + 1)) && (arr[arrLen] == 0)) {
                    arrLen++;
                }
                arr[j + 1] += arr[j] / 10;
                arr[j] %= 10;
            }
        }
        return arrLen;
    }

}
