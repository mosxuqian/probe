package com.blinkfox.test.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileInputStream和FileOutputStream的测试使用示例类.
 *
 * @author blinkfox on 2017-10-18.
 */
public class FileStreamTest {

    private static final Logger log = LoggerFactory.getLogger(FileStreamTest.class);

    /**
     * 测试复制文件a.txt中的内容到b.txt文件中.
     */
    private static void testCopyByFileStream() {
        try (
            InputStream in = new FileInputStream("G:/test/a.txt");
            OutputStream out = new FileOutputStream("G:/test/b.txt", true)
        ) {
            int read;
            byte[] b = new byte[1024];
            while ((read = in.read(b)) != -1) {
                out.write(b, 0, read);
            }
        } catch (IOException e) {
            log.error("文件读取写入失败!", e);
        }
    }

    /**
     * 主入口方法.
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        testCopyByFileStream();
    }

}