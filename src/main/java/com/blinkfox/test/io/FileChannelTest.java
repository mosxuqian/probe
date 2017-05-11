package com.blinkfox.test.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.LongBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

/**
 * FileChannel单元测试类.
 * Created by blinkfox on 2017/5/11.
 */
public class FileChannelTest {

    private static final ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);

    @Test
    public void testReadAndWirte() throws IOException {
        final long startTime = System.currentTimeMillis();

        // 得到输入、输出的FileChannel
        FileInputStream fis = new FileInputStream("/Users/blinkfox/Downloads/phonenum.txt");
        FileChannel inChannel = fis.getChannel();
        File outFile = new File("/Users/blinkfox/Downloads/out.txt");
        FileOutputStream fos = new FileOutputStream(outFile);
        FileChannel outChannel = fos.getChannel();

        // 读取内容写入文件
        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        outChannel.close();
        fos.close();

        System.out.println("执行完毕！:" + (System.currentTimeMillis() - startTime) + " ms");
    }

    @Test
    public void readLine() throws IOException {
        final long startTime = System.currentTimeMillis();

        // 得到输入、输出的FileChannel
        FileInputStream fis = new FileInputStream("/Users/blinkfox/Downloads/phonenum.txt");
        FileChannel inChannel = fis.getChannel();
        MappedByteBuffer byteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder decoder = charset.newDecoder();
        CharBuffer charBuffer = decoder.decode(byteBuffer);
        Scanner scanner = new Scanner(charBuffer).useDelimiter(System.getProperty("line.separator"));

        int currLine = 0;
        List<String> lines = new ArrayList<String>();
        while (scanner.hasNext()) {
            currLine += 1;
            lines.add(scanner.next());
            if (currLine >= 10) {
                break;
            }
        }
        System.out.println("手机号:" + lines.toString());

        inChannel.close();
        fis.close();
        System.out.println("执行完毕！:" + (System.currentTimeMillis() - startTime) + " ms");
    }

    @Test
    public void readByChannelMap() throws IOException {
        FileChannel inChannel = new FileInputStream("/Users/blinkfox/Downloads/new.txt").getChannel();
        LongBuffer buf = inChannel.map(FileChannel.MapMode.READ_ONLY,0, inChannel.size()).asLongBuffer();
        long c;
        while (buf.hasRemaining()) {
            c = buf.get();
            System.out.println(c);
        }
        inChannel.close();
    }

}