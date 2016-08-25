package com.blinkfox.patterns.facade;

/**
 * 外观门面类
 * Created by blinkfox on 16/8/25.
 */
public class Facade {

    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private ClassC c = new ClassC();

    // 提供给外部访问的方法
    public void methodA() {
        this.a.doSomethingA();
    }

    public void methodB() {
        this.b.doSomethingB();
    }

    public void methodC() {
        this.c.doSomethingC();
    }

}