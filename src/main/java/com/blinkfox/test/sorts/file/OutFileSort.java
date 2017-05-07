package com.blinkfox.test.sorts.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 外部文件排序.
 * Created by blinkfox on 2017/5/7.
 */
public class OutFileSort {

    long a = 13880545209L;

    static class Memory {

        public static long getMaxHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getMax();
        }

        public static long getInitHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getInit();
        }

        public static long getUsedHeapMemory() {
            MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
            return mmb.getHeapMemoryUsage().getUsed();
        }
    }

    public static int binaryInsertSort(List<Integer> a, int b) {// 折半插入排序
        int low = 0, high = a.size() - 1;
        while (low <= high) {
            int m = (low + high) / 2;

            if (b == a.get(m)) {
                while (m < a.size() && b == a.get(m)) {
                    m++;
                }
                high = m - 1;
                break;
            } else if (b > a.get(m)) {
                high = m - 1;
            } else {
                low = m + 1;
            }
        }
        a.add(high + 1, b);
        return high + 1;
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        int memorySize = (int) Memory.getMaxHeapMemory() / 8;
        long maxLen = memorySize;
        List<Integer> list = new ArrayList<Integer>();
        int filenum = 0;
        BufferedReader reader = null;
        FileChannel mergesortFile = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("sort.txt")));
            String numstr = reader.readLine();
            while (numstr != null) {
                list.add(Integer.parseInt(numstr));
                maxLen -= numstr.length();
                if (maxLen <= 0) {
                    maxLen = memorySize;
                    Collections.sort(list);
                    filenum++;
                    FileOutputStream fout = null;
                    try {
                        fout = new FileOutputStream("sort" + filenum + ".txt");
                        FileChannel mbb = fout.getChannel();

                        for (int i = 0; i < list.size(); i++) {
                            mbb.write(ByteBuffer.wrap(String.valueOf(
                                    list.get(i)).getBytes()));
                            mbb.write(ByteBuffer.wrap("\n".getBytes()));
                        }
                        mbb.close();
                    } finally {
                        list.clear();
                        System.out.println(list.size());
                        fout.close();
                    }

                }

                numstr = reader.readLine();
            }
            if (maxLen != (memorySize)) {
                Collections.sort(list);
                filenum++;
                FileOutputStream fout = null;
                try {
                    fout = new FileOutputStream("sort" + filenum + ".txt");
                    FileChannel mbb = fout.getChannel();

                    for (int i = 0; i < list.size(); i++) {
                        mbb.write(ByteBuffer.wrap(String.valueOf(list.get(i))
                                .getBytes()));
                        mbb.write(ByteBuffer.wrap("\n".getBytes()));
                    }
                    mbb.close();
                } finally {
                    list.clear();
                    System.out.println(list.size());
                    fout.close();
                }
            }
            list = null;
            try {

                List<BufferedReader> fileChannelList = new ArrayList<BufferedReader>();
                for (int i = 1; i <= filenum; i++) {
                    BufferedReader subFileReader = new BufferedReader(
                            new InputStreamReader(new FileInputStream("sort"
                                    + i + ".txt")));
                    fileChannelList.add(subFileReader);
                }
                @SuppressWarnings("resource")
                FileOutputStream fout1 = new FileOutputStream("mergersort.txt");
                mergesortFile = fout1.getChannel();
                List<BufferedReader> filechanlIndexList = new ArrayList<BufferedReader>();
                List<Integer> numList = new ArrayList<Integer>();
                if (fileChannelList.size() > 0) {
                    for (int i = 0; i < fileChannelList.size(); i++) {
                        String sunnumstr = fileChannelList.get(i).readLine();
                        if (sunnumstr != null) {
                            int index = binaryInsertSort(numList,
                                    Integer.parseInt(sunnumstr));
                            filechanlIndexList.add(index,
                                    fileChannelList.get(i));
                        } else {
                            fileChannelList.remove(i).close();
                        }
                    }
                    fileChannelList.clear();
                    while (filechanlIndexList.size() > 0) {
                        if (filechanlIndexList.size() != numList.size()) {
                            throw new RuntimeException("list.size["
                                    + numList.size()
                                    + "] != filechanlIndexList.size["
                                    + filechanlIndexList.size() + "] ");
                        }
                        mergesortFile
                                .write(ByteBuffer.wrap(String.valueOf(
                                        numList.remove(numList.size() - 1))
                                        .getBytes()));
                        mergesortFile.write(ByteBuffer.wrap("\n".getBytes()));
                        BufferedReader mixBufferedReader = filechanlIndexList
                                .remove(filechanlIndexList.size() - 1);
                        String sunnumstr = mixBufferedReader.readLine();
                        if (sunnumstr != null) {
                            int index = binaryInsertSort(numList,
                                    Integer.parseInt(sunnumstr));
                            filechanlIndexList.add(index, mixBufferedReader);
                        } else {
                            mixBufferedReader.close();
                        }
                    }

                }
                for (int i = 1; i <= filenum; i++) {
                    File file = new File("sort" + i + ".txt");
                    if (file.exists() && file.isFile()) {
                        file.delete();
                    }
                }
            } finally {
                mergesortFile.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}