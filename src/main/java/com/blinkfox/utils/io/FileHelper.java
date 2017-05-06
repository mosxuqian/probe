package com.blinkfox.utils.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作、流的工具类.
 * Created by blinkfox on 2017-05-06.
 */
public class FileHelper {

    /**
     * 根据文件名读取文本内容到一个集合中，每一行就是好集合的一个元素.
     * @param fileName 文件全路径名
     */
    public static List<String> readLines(String fileName) {
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(is));
            List<String> lines = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            return lines;
        } catch (IOException e) {
            System.out.println("读取文件出错！");
            e.printStackTrace();
            return null;
        } finally {
            closeQuietly(is);
            closeQuietly(reader);
        }
    }

    /**
     * 将集合中的数据输出到指定文件名的文件中.
     * @param fileName 文件全路径名
     * @param lines 集合
     */
    public static void writeLines(String fileName, List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return;
        }

        OutputStream output = null;
        try {
            output = new FileOutputStream(fileName);
            for (String line: lines) {
                if (line != null) {
                    output.write(line.toString().getBytes());
                    output.write("\n".getBytes());
                }
            }
            output.close();
        } catch (IOException e) {
            System.out.println("生成输出流出错！");
            e.printStackTrace();
        } finally {
            closeQuietly(output);
        }
    }

    /**
     * 关闭Reader.
     * @param reader reader
     */
    public static void closeQuietly(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException rex) {
                System.out.println("关闭输入流出错！");
                rex.printStackTrace();
            }
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
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        List<String> lines = FileHelper.readLines("H:\\phonenum_min.txt");
        System.out.println("lines json:" + (lines == null ? "null" : lines.toString()));

        String fileName = new StringBuilder("H:\\").append(System.currentTimeMillis()).append("_temp.txt").toString();
        FileHelper.writeLines(fileName, lines);
        System.out.println("写文件完成!");
    }

}