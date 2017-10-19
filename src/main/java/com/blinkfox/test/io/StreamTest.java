package com.blinkfox.test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

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
     * 测试将内容写入到ByteArrayOutputStream中并打印出来，不需要关闭流.
     */
    private static void testByByteArrayStream() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(8);
        String str = "Hello World!";
        try {
            byteOut.write(str.getBytes());
        } catch (IOException e) {
            log.error("写入字节数据出错!", e);
        }

        byte[] buf = byteOut.toByteArray();
        for (byte b : buf) {
            log.info("{}", (char) b);
        }
    }

    /**
     * 测试使用 testBySequenceStream 合并输入流来统一读取写入.
     */
    private static void testBySequenceStream() {
        try (
            InputStream in1 = new FileInputStream("G:/test/a.txt");
            InputStream in2 = new FileInputStream("G:/test/b.txt");
            OutputStream out = new FileOutputStream("G:/test/c.txt");
            SequenceInputStream seqIn = new SequenceInputStream(in1, in2)
        ) {
            int len = 0;
            while ((len = seqIn.read()) != -1) {
                out.write(len);
            }
        } catch (IOException e) {
            log.error("合并输入流写入失败!", e);
        }
    }

    /**
     * 测试通过 BufferedInputStream 和 BufferedOutputStream 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void testCopyByBufferedStream() {
        try (
            InputStream in = new BufferedInputStream(new FileInputStream("G:/test/a.txt"));
            OutputStream out = new BufferedOutputStream(new FileOutputStream("G:/test/b.txt"))
        ) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("通过缓冲区的方式来做文件读取写入失败!", e);
        }
    }

    /**
     * 主入口方法.
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        //testCopyByFileStream();
        //testByByteArrayStream();
        //testBySequenceStream();
        testCopyByBufferedStream();
    }

}