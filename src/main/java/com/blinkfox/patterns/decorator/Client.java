package com.blinkfox.patterns.decorator;

/**
 * 装饰模式的客户端场景类
 * Created by blinkfox on 16-6-26.
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        // 第一修饰
        component = new ConcreteDecorator1(component);

        // 第二修饰
        component = new ConcreteDecorator2(component);

        // 修饰后运行
        component.operate();
    }

}