package com.blinkfox.test.sorts.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileSortDemo.
 */
public class FileSortDemo {

    private static byte[] datas = new byte[100000000];

    private static final int bufferPhoneLength = 100000;
    private static final int bufferLength = 13 * bufferPhoneLength;


    public static void readBuffer() throws IOException {
        String filename = "/Users/blinkfox/Downloads/phonenum.txt";
        FileInputStream fin = new FileInputStream(new File(filename));
        FileChannel channel = fin.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(bufferLength);
        while (channel.read(bf) != -1) {
            bf.flip();
            sord(bf);
        }
        bf.clear();
        fin.close();
    }

    public static void sord(ByteBuffer bf) throws IOException {
        // 一行手机号
        byte[] dst = new byte[13];
        while (bf.hasRemaining()) {
            bf.get(dst);
            String phone = new String(dst, 3, 8);
            Integer phoneindex = Integer.valueOf(phone);
            datas[phoneindex] = 1;
        }
        bf.clear();
    }

    public static void write(String filename) throws IOException {
        FileOutputStream fou = new FileOutputStream(new File(filename));
        FileChannel channel = fou.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(bufferLength);
        int writeNum = 1;
        for (int i = 0, l = datas.length; i < l - 50000000; i++) {
            //封装
            if (datas[i] == 1) {
                bf.put(index2num(i));
            }
            writeNum++;
            if (writeNum >= bufferPhoneLength) {
                writeNum = 1;
                //写
                bf.flip();
                channel.write(bf);
                bf.clear();
            }
        }
        bf.flip();
        channel.write(bf);

        bf.rewind();
        channel.close();
        fou.close();

    }

    public static byte[] index2num(int idx) {
        byte[] phone = new byte[13];
        phone[0] = 49;
        phone[1] = 51;
        phone[2] = 56;
        for (int i = 3; i <= 10; i++) {
            phone[i] = 48;
        }
        phone[11] = 13;
        phone[12] = 10;
        byte[] idxbytes = String.valueOf(idx).getBytes();
        int j = 0;
        for (int i = idxbytes.length - 1; i >= 0; i--) {
            phone[10 - j] = idxbytes[i];
            j++;
        }
        return phone;

    }

    public static void main(String[] args) throws IOException {
        String outputFilename = "/Users/blinkfox/Downloads/phonenum_sss.txt";

        final long startTime = System.currentTimeMillis();
        readBuffer();

        write(outputFilename);
        System.out.println("执行完毕！:" + (System.currentTimeMillis() - startTime) + " ms");
    }

}