package com.blinkfox.test.io;

import com.blinkfox.utils.Log;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件内容追加的示例类
 * Created by blinkfox on 2017-03-07.
 */
public class AppendToFile {

    private static final Log log = Log.get(AppendToFile.class);

    /**
     * 私有构造方法
     */
    private AppendToFile() {
        super();
    }

    /**
     * 主方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        String fileName = "F:\\test\\test.md";
        String content = "new append!";
        AppendToFile.appendWithRandomFile(fileName, content);
        ReadFromFile.readFileByLine(fileName);
        AppendToFile.appendWithFileWriter(fileName, content);
        ReadFromFile.readFileByLine(fileName);
    }

    /**
     * 追加文件：使用RandomAccessFile
     * @param fileName 文件路径及名称
     * @param content 待追加的内容
     */
    public static void appendWithRandomFile(String fileName, String content) {
        RandomAccessFile randomFile = null;
        try {
            randomFile = new RandomAccessFile(fileName, "rw");
            long fileLen = randomFile.length();
            randomFile.seek(fileLen);
            randomFile.writeBytes(content);
        } catch (Exception e) {
            log.error("追加文件内容异常!", e);
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e) {
                    log.error("关闭文件异常!", e);
                }
            }
        }
    }

    /**
     * 追加文件：使用FileWriter
     * @param fileName 文件路径及名称
     * @param content 待追加的内容
     */
    public static void appendWithFileWriter(String fileName, String content) {
        FileWriter writer = null;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (Exception e) {
            log.error("追加文件内容异常!", e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                log.error("关闭文件异常!", e);
            }
        }
    }

}