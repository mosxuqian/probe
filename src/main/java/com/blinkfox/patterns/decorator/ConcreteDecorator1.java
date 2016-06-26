package com.blinkfox.patterns.decorator;

/**
 * 具体的装饰类1
 * Created by blinkfox on 16-6-26.
 */
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    /**
     * 定义自己的修饰方法1
     */
    private void method1() {
        System.out.println("method1修饰...");
    }

    /**
     * 重写父类的operate方法
     */
    public void operate() {
        this.method1();
        super.operate();
    }

}