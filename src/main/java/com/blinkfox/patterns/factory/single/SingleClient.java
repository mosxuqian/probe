package com.blinkfox.patterns.factory.single;

/**
 * 工厂方法单例模式客户端场景类
 * Created by blinkfox on 16-7-4.
 */
public class SingleClient {

    public static void main(String[] args) {
        Singleton singleton = SingletonFactory.getSingleton();
        singleton.doSomething();
    }

}