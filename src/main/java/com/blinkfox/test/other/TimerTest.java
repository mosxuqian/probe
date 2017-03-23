package com.blinkfox.test.other;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.pmw.tinylog.Logger;

/**
 * java.util.Timer使用示例类.
 * Created by blinkfox on 2017-03-23.
 */
public class TimerTest {

    private static final long ONE_DAY = 1000 * 60 * 60 * 24;

    /**
     * 私有构造方法.
     */
    private TimerTest() {
        super();
    }

    /**
     * 延迟2秒执行.
     */
    private static void time1() {
        Logger.info("开始执行time1任务，时间为:{}", LocalTime.now());

        // 延时两秒执行
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Logger.info("time1任务执行，时间为:{}", LocalTime.now());
            }
        }, 2000);

        Logger.info("执行了time1任务，时间为:{}", LocalTime.now());
    }

    /**
     * 延迟2秒之后每隔3秒循环执行.
     */
    private static void time2() {
        Logger.info("开始执行time2任务，时间为:{}", LocalTime.now());

        // 延时两秒执行
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Logger.info("time2任务执行，时间为:{}", LocalTime.now());
            }
        }, 2000, 3000);

        Logger.info("执行了time2任务，时间为:{}", LocalTime.now());
    }

    /**
     * 延迟2秒之后每隔3秒循环执行，修复延迟对执行频率的影响.
     */
    private static void time3() {
        Logger.info("开始执行time3任务，时间为:{}", LocalTime.now());

        // 延时两秒执行
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Logger.info("time3任务执行，时间为:{}", LocalTime.now());
            }
        }, 2000, 3000);

        Logger.info("执行了time3任务，时间为:{}", LocalTime.now());
    }

    /**
     * 延迟2秒之后每隔3秒循环执行，修复延迟对执行频率的影响.
     */
    private static void time4() {
        // 初始化出执行任务的时间,此处为今天的12：00：00
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();

        Logger.info("开始执行time4任务，时间为:{}", LocalTime.now());

        // 延时两秒执行
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Logger.info("time4任务正式执行，时间为:{}" + LocalTime.now());
            }
        }, time, ONE_DAY);

        Logger.info("执行了time4任务，时间为:{}", LocalTime.now());
    }

    /**
     * ScheduledExecutorService实现的定时任务.
     * ScheduledExecutorService是从Java SE5的java.util.concurrent里，做为并发工具类被引进的，这是最理想的定时任务实现方式。
     * 相比于上两个方法，它有以下好处：
     * 1>相比于Timer的单线程，它是通过线程池的方式来执行任务的
     * 2>可以很灵活的去设定第一次执行任务delay时间
     * 3>提供了良好的约定，以便设定执行的时间间隔
     */
    private static void task() {
        Logger.info("开始执行task任务，时间为:{}", LocalTime.now());

        Runnable runnable = () -> Logger.info("正在使用 ScheduledExecutorService 执行定时任务，时间为:{}", LocalTime.now());
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(runnable, 10, 2, TimeUnit.SECONDS);

        Logger.info("执行了task任务，时间为:{}", LocalTime.now());
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        // time4();
        task();
    }

}