package com.blinkfox.test.io.pipe;

import java.io.IOException;
import java.io.PipedInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收消息的线程.
 *
 * @author blinkfox on 2017/10/19.
 */
public class Receiver extends Thread {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    /** 管道输入流对象,它和管道输出流(PipedOutputStream)对象绑定.从而可以接收“管道输出流”的数据. */
    private PipedInputStream pipedIn;

    public Receiver(PipedInputStream pipedIn) {
        this.pipedIn = pipedIn;
    }

    public PipedInputStream getPipedIn() {
        return pipedIn;
    }

    @Override
    public void run() {
        byte[] buf = new byte[2048];
        try {
            int len = pipedIn.read(buf);
            log.info("{}", new String(buf, 0, len));
            pipedIn.close();
        } catch (IOException e) {
            log.error("从管道中读取数据出错!", e);
        }
    }

}