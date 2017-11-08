package com.blinkfox.test.io.nio;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IoNioTest.
 *
 * @author blinkfox on 2017-11-08.
 */
public class IoNioTest {

    private static final Logger log = LoggerFactory.getLogger(IoNioTest.class);

    private static final String SRC = "G:\\test\\copytest\\a.txt";

    private static final String DEST = "G:\\test\\copytest\\b.txt";

    /**
     * 使用Stream来copy.
     */
    private static void copyByStream(String src, String dest) {
        long start = System.currentTimeMillis();
        try (
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest, true)
        ) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("通过原始 Stream 来copy失败!", e);
        }
        log.info("通过原始 Stream 来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 BufferedInputStream 和 BufferedOutputStream 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByBufferedStream(String src, String dest) {
        long start = System.currentTimeMillis();
        try (
            InputStream in = new BufferedInputStream(new FileInputStream(src));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dest))
        ) {
            int len;
            byte[] b = new byte[1024];
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
        } catch (IOException e) {
            log.error("通过缓冲区的方式来做文件读取写入失败!", e);
        }
        log.info("通过 BufferedStream 来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 BufferedInputStream 和 BufferedOutputStream 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByWriter(String src, String dest) {
        long start = System.currentTimeMillis();
        try (
            Reader reader = new FileReader(src);
            Writer writer = new FileWriter(dest)
        ) {
            int len;
            char[] buf = new char[1024];
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }
        } catch (IOException e) {
            log.error("通过 Writer 的方式来做文件读取写入失败!", e);
        }
        log.info("通过 Writer 来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 BufferedInputStream 和 BufferedOutputStream 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByBufferedWriter(String src, String dest) {
        long start = System.currentTimeMillis();
        try (
            BufferedReader reader = new BufferedReader(new FileReader(src));
            BufferedWriter writer = new BufferedWriter(new FileWriter(dest))
        ) {
            String str;
            while ((str = reader.readLine()) != null) {
                writer.write(str);
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("通过BufferedWriter的方式来做文件读取写入失败!", e);
        }
        log.info("通过BufferedWriter来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 FileChannel 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByNioFileChannel(String src, String dest) {
        long start = System.currentTimeMillis();

        try (
            FileChannel fic = new FileInputStream(src).getChannel();
            FileChannel foc = new FileOutputStream(dest).getChannel()
        ) {
            // 如果目标文件已经存在，则会抛错!
            foc.transferFrom(fic, 0, fic.size());
        } catch (IOException e) {
            log.error("通过 FileChannel 的方式来做文件读取写入失败!", e);
        }
        log.info("通过 FileChannel 的方式来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 NIO Files 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByNioFiles(String src, String dest) {
        long start = System.currentTimeMillis();

        try {
            // 如果目标文件已经存在，则会抛错!
            Files.copy(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            log.error("通过NIO Files的方式来做文件读取写入失败!", e);
        }
        log.info("通过NIO Files的方式来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    /**
     * 测试通过 MappedByteBuffer 来复制文件a.txt中的内容到b.txt文件中.
     */
    private static void copyByMappedByteBuffer(String src, String dest) {
        long start = System.currentTimeMillis();

        try (
            FileChannel fic = new FileInputStream(src).getChannel();
            FileChannel foc = new FileOutputStream(dest).getChannel()
        ) {
            // 如果目标文件已经存在，则会抛错!
            long size = fic.size();
            MappedByteBuffer inMbb = fic.map(FileChannel.MapMode.READ_ONLY, 0 , size);
            foc.write(inMbb);
        } catch (IOException e) {
            log.error("通过 MappedByteBuffer 的方式来做文件读取写入失败!", e);
        }
        log.info("通过 MappedByteBuffer 的方式来copy的耗时:{} ms", System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
//        copyByStream(SRC, DEST);
//        copyByWriter(SRC, DEST);
//        copyByBufferedStream(SRC, DEST);
//        copyByBufferedWriter(SRC, DEST);
//        copyByNioFileChannel(SRC, DEST);
//        copyByNioFiles(SRC, DEST);
        copyByMappedByteBuffer(SRC, DEST);
    }

}