package com.blinkfox.patterns.decorator;

/**
 * 装饰角色
 * 维持一个指向Component对象的引用，并定义一个与 Component接口一致的接口。
 * Created by blinkfox on 16-6-26.
 */
public class Decorator implements Component {

    private Component component;

    /**
     * 通过构造函数传递被修饰者
     * @param component
     */
    public Decorator(Component component) {
        this.component = component;
    }

    /**
     * 委托给被修饰者执行
     */
    @Override
    public void operate() {
        this.component.operate();
    }

}