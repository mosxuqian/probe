package com.blinkfox.test.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
public class StreamTest {

    private static final Logger log = LoggerFactory.getLogger(StreamTest.class);

    /**
     * 测试复制文件a.txt中的内容到b.txt文件中.
     */
    private static void testCopyByFileStream() {
        try (
            InputStream in = new FileInputStream("G:/test/a.txt");
            OutputStream out = new FileOutputStream("G:/test/b.txt", true)
        ) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("文件读取写入失败!", e);
        }
    }

    /**
     * 测试将内容输写入到ByteArrayOutputStream中并打印出来，不需要关闭流.
     */
    private static void testByByteArrayStream() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(8);
        try {
            byteOut.write(new byte[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'});
        } catch (IOException e) {
            log.error("写入字节数据出错!", e);
        }

        byte[] buf = byteOut.toByteArray();
        for (byte b : buf) {
            log.info("{}", (char) b);
        }
        log.info("打印字节数组中的内容结束!");
    }

    /**
     * 主入口方法.
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        //testCopyByFileStream();
        testByByteArrayStream();
    }

}