package com.blinkfox.test.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计数的示例.
 *
 * @author blinkfox on 2018/5/23.
 */
public class CountExample {

    private static final Logger log = LoggerFactory.getLogger(CountExample.class);

    /** 客户端数量. */
    private static final int CLIENT_NUMBER = 5000;

    /** 最大并发线程数量. */
    private static final int THREAD_NUMBER = 200;

    /** 测试的全局变量count. */
    private static LongAdder adder = new LongAdder();

    /**
     * main方法.
     *
     * @param args 数组参数
     */
    public static void main(String[] args) throws InterruptedException {
        log.info("开始执行计算...");
        ExecutorService exeService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(THREAD_NUMBER);
        CountDownLatch countDownLatch = new CountDownLatch(5000);

        for (int i = 0; i < CLIENT_NUMBER; i++) {
            exeService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("semaphore获取许可证异常.", e);
                    Thread.currentThread().interrupt();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        exeService.shutdown();
        log.info("执行完毕，count的值为:{}", adder.longValue());
    }

    /**
     * 累加count的值.
     */
    public static void add() {
        adder.increment();
    }

}