package com.blinkfox.patterns.proxy;

/**
 * 代理类
 * Created by blinkfox on 2017/1/1.
 */
public class Proxy implements ISubject {

    private ISubject subject;

    public Proxy(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    /**
     * 预处理
     */
    private void before() {
        System.out.println("执行前(before)的处理...");
    }

    /**
     * 善后处理
     */
    private void after() {
        System.out.println("执行后(after)的处理...");
    }

}