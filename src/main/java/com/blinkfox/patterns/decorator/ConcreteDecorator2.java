package com.blinkfox.patterns.decorator;

/**
 * Created by blinkfox on 16-6-26.
 */
public class ConcreteDecorator2 extends Decorator {
    /**
     * 通过构造函数传递被修饰者
     *
     * @param component
     */
    public ConcreteDecorator2(Component component) {
        super(component);
    }

    /**
     * 定义自己的修饰方法2
     */
    private void method2() {
        System.out.println("method2修饰...");
    }

    /**
     * 重写父类的operate方法
     */
    public void operate() {
        this.method2();
        super.operate();
    }

}