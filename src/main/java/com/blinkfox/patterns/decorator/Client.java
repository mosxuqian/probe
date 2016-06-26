package com.blinkfox.patterns.decorator;

/**
 * Created by blinkfox on 16-6-26.
 */
public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();

        // 第一修饰
        component = new ConcreteDecorator1(component);

        // 第二修饰
        component = new ConcreteDecorator2(component);

        component.operate();
    }

}