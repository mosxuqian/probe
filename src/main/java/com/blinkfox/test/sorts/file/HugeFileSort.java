package com.blinkfox.test.sorts.file;

import com.fasterxml.sort.SortConfig;
import com.fasterxml.sort.std.TextFileSorter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 大文件排序.
 * Created by blinkfox on 2017/5/4.
 */
public class HugeFileSort {

    /**
     * 排序方法.
     * @param inputFileName 输入文件名称
     * @param outputFileName 输出文件名称
     * @throws IOException 异常
     */
    private static void sort(String inputFileName, String outputFileName) throws IOException {
        TextFileSorter sorter = new TextFileSorter(new SortConfig().withMaxMemoryUsage(48*1024*1024));
        sorter.sort(new FileInputStream(inputFileName), new FileOutputStream(outputFileName));
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        // sort("/Users/blinkfox/Downloads/phonenum.txt", "/Users/blinkfox/Downloads/phonenum2.txt");
        sort("H:\\programs\\phonenum\\phonenum.txt", "H:\\programs\\phonenum\\phonenum_sort.txt");
        System.out.println("排序完成!，耗时:" + (System.currentTimeMillis() - startTime) + " ms");
    }

}