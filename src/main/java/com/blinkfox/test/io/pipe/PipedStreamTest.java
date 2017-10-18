package com.blinkfox.test.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PipedInputStream 和 PipedOutputStream 的测试类.
 *
 * @author blinkfox on 2017/10/19.
 */
public class PipedStreamTest {

    private static final Logger log = LoggerFactory.getLogger(PipedStreamTest.class);

    /**
     * 主入口方法.
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        Sender sender = new Sender(new PipedOutputStream());
        Receiver receiver = new Receiver(new PipedInputStream());

        try {
            // 将管道输入流和管道的输出流进行连接.
            receiver.getPipedIn().connect(sender.getPipedOut());

            // 启动线程
            sender.start();
            receiver.start();
        } catch (IOException e) {
            log.info("发送接收消息出错!", e);
        }
    }

}