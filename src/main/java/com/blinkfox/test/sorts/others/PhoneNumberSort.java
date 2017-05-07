package com.blinkfox.test.sorts.others;

import java.io.*;
import java.util.*;

/**
 * PhonenumSort.
 * Created by blinkfox on 2017/5/7.
 */
public class PhoneNumberSort {

    /* 临时文件最大行数 */
    private static final int MAX_LINE = 4800000;

    /* 输入文件 */
    private static final String INPUT_FILE_NAME = "/Users/blinkfox/Downloads/phonenum.txt";

    /* 输出文件 */
    private static final String OUTPUT_FILE_NAME = "/Users/blinkfox/Downloads/phonenum_sort.txt";

    /* 输出目录 */
    private static final String OUTPUT_DIRECTORY = "/Users/blinkfox/Downloads/phonenum_temp/";

    private static final List<File> tempFiles = new ArrayList<File>();

    static {
        File file = new File(OUTPUT_DIRECTORY);
        file.mkdirs();
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("该目录不存在!");
        }
    }

    /**
     * main方法.
     * @param args 数组参数
     * @throws IOException 异常
     */
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("排序开始,当前时间为:" + startTime);

        // 先拆分原始的大文件为各个有序的小文件，最后使用归并排序的实现将各个小文件合并成一个有序的大文件.
        splitFiles();
        System.out.println("拆分成小文件结束，开始合并成大文件");

        long startTime1 = System.currentTimeMillis();
        mergeFiles();
        System.out.println("合并成大文件结束,执行耗时:" + (System.currentTimeMillis() - startTime1) + " ms");

        System.out.println("排序结束,执行耗时:" + (System.currentTimeMillis() - startTime) + " ms");
    }

    /**
     * 拆分大文件为各个小文件.
     */
    private static void splitFiles() {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = new FileInputStream(INPUT_FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(is));
            List<Long> phoneNums = new ArrayList<Long>();

            // 读取代码行数，并拆分成不同的数据输出到多个临时文件中
            int currLines = 0;
            String line = null;
            while ((line = reader.readLine()) != null) {
                currLines += 1;
                phoneNums.add(Long.parseLong(line));

                // 如果当前读取的行数大于了最大行数，则排序目前的集合，且输出到临时文件中
                if (currLines >= MAX_LINE) {
                    sortAndOutputFile(phoneNums);
                    currLines = 0;
                }
            }

            // 排序和输出剩余的电话号码
            sortAndOutputFile(phoneNums);
        } catch (IOException e) {
            System.out.println("拆分大文件为各个小文件出错！");
            e.printStackTrace();
        } finally {
            closeQuietly(is);
            closeQuietly(reader);
        }
    }

    /**
     * 合并多个有序的文件为一个有序的大文件.
     */
    private static void mergeFiles() {
        List<Reader> readers = new ArrayList<Reader>();
        Map<Long, BufferedReader> readerMap = new HashMap<Long, BufferedReader>();

        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            // 将各个小文件的基本信息初始化到Reader集合和Map中，供接下来的排序时使用
            for (File tempFile: tempFiles) {
                // 生成各临时文件的FileReader并存到集合中，这样好在最后关闭所有的FileReader
                fileReader = new FileReader(tempFile);
                readers.add(fileReader);

                // 生成BufferedReader并存到HashMap中，供接下来好存、取Map中的R数据
                BufferedReader reader = new BufferedReader(fileReader);
                String phoneNum = reader.readLine();
                if (phoneNum != null) {
                    readerMap.put(Long.parseLong(phoneNum), reader);
                }
            }

            // 采用归并排序的思想,一直循环排序直到各个文件没有了电话号码为止
            fileWriter = new FileWriter(OUTPUT_FILE_NAME);
            writer = new BufferedWriter(fileWriter);
            List<Long> phoneNums = new LinkedList<Long>(readerMap.keySet());
            while (readerMap.size() > 0) {
                // 排序，将最大的手机号从phoneNums集合中移除，并写入到输出文件中
                Collections.sort(phoneNums, LongComparator.getInstance());
                Long phoneNum = phoneNums.remove(0);
                writer.append(toNumStr(phoneNum)).append('\n');

                // 从readerMap中移除刚才得出的最大手机号,并从该reader中读取下一个手机号存到List和Map中，继续循环写入
                BufferedReader reader = readerMap.remove(phoneNum);
                String nextPhoneNumStr = reader.readLine();
                if (nextPhoneNumStr != null) {
                    Long nextPhoneNum = Long.parseLong(nextPhoneNumStr);
                    phoneNums.add(nextPhoneNum);
                    readerMap.put(nextPhoneNum, reader);
                }
            }
        } catch (IOException e) {
            System.out.println("合并多个有序的文件为一个有序的大文件出错！");
            e.printStackTrace();
        } finally {
            closeQuietly(fileReader);
            closeQuietly(writer);
            closeQuietly(fileWriter);
        }
    }

    /**
     * 排序集合数据，并输出到临时文件中文件.
     * @param phoneNums 电话号码集合
     */
    private static void sortAndOutputFile(List<Long> phoneNums) {
        long startTime = System.currentTimeMillis();
        Collections.sort(phoneNums, LongComparator.getInstance());
        System.out.println("排序集合数据耗时:" + (System.currentTimeMillis() - startTime) + " ms");

        // 输出到临时文件
        long startTime1 = System.currentTimeMillis();
        String outputFileName = new StringBuilder(OUTPUT_DIRECTORY)
                .append(System.currentTimeMillis()).append("_temp.txt").toString();
        File file = new File(outputFileName);
        tempFiles.add(file);
        writeLines(file, phoneNums);

        System.out.println("输出了临时文件,耗时:" + (System.currentTimeMillis() - startTime1) + " ms");

        // 清空集合数据
        phoneNums.clear();
    }

    /**
     * 将集合中的数据输出到指定文件名的文件中.
     * @param file 文件
     * @param lines 集合
     */
    public static void writeLines(File file, List<Long> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }

        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for (Long line: lines) {
                if (line != null) {
                    writer.append(toNumStr(line)).append('\n');
                }
            }
        } catch (IOException e) {
            System.out.println("生成输出流出错！");
            e.printStackTrace();
        } finally {
            closeQuietly(writer);
            closeQuietly(fw);
        }
    }

    /**
     * 关闭输入流.
     * @param input 输入流
     */
    public static void closeQuietly(InputStream input) {
        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException inex) {
            System.out.println("关闭输入流出错！");
            inex.printStackTrace();
        }
    }

    /**
     * 关闭Reader.
     * @param reader reader
     */
    public static void closeQuietly(Reader reader) {
        closeQuietly(Arrays.asList(reader));
    }

    /**
     * 关闭Reader.
     * @param readers readers
     */
    public static void closeQuietly(List<Reader> readers) {
        if (readers != null && !readers.isEmpty()) {
            try {
                for (Reader reader: readers) {
                    reader.close();
                }
            } catch (IOException rex) {
                System.out.println("关闭输入流出错！");
                rex.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流.
     * @param output 输出流
     */
    public static void closeQuietly(Writer output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            System.out.println("关闭输出流出错！");
            e.printStackTrace();
        }
    }

    /**
     * 关闭输出流.
     * @param output 输出流
     */
    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            System.out.println("关闭输出流出错！");
            e.printStackTrace();
        }
    }

    /**
     * 将long型数据转换成电话号码的11位字符串.
     * @param l 长整型数字
     * @return 字符串
     */
    public static String toNumStr(Long l) {
        String str = l.toString();
        int len = 0;
        if ((len = str.length()) == 11) {
            return str;
        }

        // 长度不是11位则在后面补0
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < 11 - len; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

}