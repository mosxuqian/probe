package com.blinkfox.test.sorts.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * StreamSorter.
 * Created by blinkfox on 2017-05-06.
 */
public class StreamSorter {

    private final Comparator<String> sorter;
    private int maxChunkSize = 100000000;
    private List<File> outputs = new ArrayList<File>();
    private String tempDirectory = "";

    /**
     * 构造方法.
     * @param sorter sorter
     */
    public StreamSorter(Comparator<String> sorter) {
        this.sorter = sorter;
    }

    /**
     * 设置临时目录.
     *
     * @param temp temp
     */
    public void setTempDirectory(String temp) {
        tempDirectory = temp;
        File file = new File(tempDirectory);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("该目录不存在!");
        }
    }

    /**
     * 设置临时文件的大小.
     *
     * @param size 大小
     */
    public void setMaximumChunkSize(int size) {
        this.maxChunkSize = size;
    }

    /**
     * 读取输入的IO流，并将其分解成被各个要写入的临时文件.
     *
     * @param in 输入流
     * @throws IOException IO异常
     */
    public void splitChunks(InputStream in) throws IOException {
        outputs.clear();
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            int currChunkSize = 0;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                currChunkSize += line.length() + 1;
                if (currChunkSize >= maxChunkSize) {
                    currChunkSize = 0;
                    lines.sort(sorter);
                    File file = new File(tempDirectory + "temp" + System.currentTimeMillis());
                    outputs.add(file);
                    writeOut(lines, new FileOutputStream(file));
                    lines.clear();
                }
            }

            //write out the remaining chunk
            // Collections.sort(lines, sorter);
            lines.sort(sorter);
            File file = new File(tempDirectory + "temp" + System.currentTimeMillis());
            outputs.add(file);
            writeOut(lines, new FileOutputStream(file));
            lines.clear();
        } catch (IOException e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将行列表写入输出流，在每行之后追加新行.
     * @param list 集合
     * @param os 输出流
     * @throws IOException IO异常
     */
    private void writeOut(List<String> list, OutputStream os) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(os));
            for (String s : list) {
                writer.write(s);
                writer.write("\n");
            }
            writer.flush();
        } catch (IOException io) {
            throw io;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取由splitChunks方法创建的临时文件，并将其按排序方式合并到输出流中.
     * @param os 输出流
     * @throws IOException IO异常
     */
    public void mergeChunks(OutputStream os) throws IOException {
        Map<StringWrapper, BufferedReader> map = new HashMap<StringWrapper, BufferedReader>();
        List<BufferedReader> readers = new ArrayList<BufferedReader>();
        BufferedWriter writer = null;
        ComparatorDelegate delegate = new ComparatorDelegate();
        try {
            for (int i = 0; i < outputs.size(); i++) {
                BufferedReader reader = new BufferedReader(new FileReader(outputs.get(i)));
                readers.add(reader);
                String line = reader.readLine();
                if (line != null) {
                    map.put(new StringWrapper(line), readers.get(i));
                }
            }

            // continue to loop until no more lines lefts
            writer = new BufferedWriter(new OutputStreamWriter(os));
            List<StringWrapper> sorted = new LinkedList<StringWrapper>(map.keySet());
            while (map.size() > 0) {
                sorted.sort(delegate);
                StringWrapper line = sorted.remove(0);
                writer.write(line.string);
                writer.write("\n");
                BufferedReader reader = map.remove(line);
                String nextLine = reader.readLine();
                if (nextLine != null) {
                    StringWrapper sw = new StringWrapper(nextLine);
                    map.put(sw, reader);
                    sorted.add(sw);
                }
            }
        } catch (IOException io) {
            throw io;
        } finally {
            for (int i = 0; i < readers.size(); i++) {
                try {
                    readers.get(i).close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < outputs.size(); i++) {
                outputs.get(i).delete();
            }

            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 委托比较器能够对StringWrapper类进行排序.
     */
    private class ComparatorDelegate implements Comparator<StringWrapper> {
        @Override
        public int compare(StringWrapper o1, StringWrapper o2) {
            return sorter.compare(o1.string, o2.string);
        }
    }

    /**
     * Class是一个String的包装类。 这对于String重复是必需的，这可能会导致equals / hashCode,在文件合并中使用的HashMap内的冲突.
     */
    private class StringWrapper implements Comparable<StringWrapper> {
        private final String string;
        public StringWrapper(String line) {
            this.string = line;
        }

        @Override
        public int compareTo(StringWrapper o) {
            return string.compareTo(o.string);
        }
    }

    public static void main(String[] args) {

    }

}