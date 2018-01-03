package com.blinkfox.test.other;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * CountTask.
 *
 * @author blinkfox on 2018-01-03.
 */
public class CountTask extends RecursiveTask<Integer> {

    /** 阈值. */
    public static final int THRESHOLD = 2;

    /** 计算的开始值. */
    private int start;

    /** 计算的结束值. */
    private int end;

    /**
     * 构造方法.
     *
     * @param start 计算的开始值
     * @param end 计算的结束值
     */
    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 执行计算的方法.
     *
     * @return int型结果
     */
    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务.
        if ((end - start) <= THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务来计算.
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            // 等待子任务执行完，并得到结果，再合并执行结果.
            leftTask.fork();
            rightTask.fork();
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }

    /**
     * main方法.
     *
     * @param args 数组参数
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool fkPool = new ForkJoinPool();
        CountTask task = new CountTask(1, 4);
        Future<Integer> result = fkPool.submit(task);
        System.out.println("result:" + result.get());
    }

}