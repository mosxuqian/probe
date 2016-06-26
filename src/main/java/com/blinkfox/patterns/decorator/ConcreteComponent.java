package com.blinkfox.patterns.decorator;

/**
 * 具体构件
 * Created by blinkfox on 16-6-26.
 */
public class ConcreteComponent implements Component {

    /**
     * 具体实现方法
     */
    @Override
    public void operate() {
        System.out.println("do Something...");
    }

}