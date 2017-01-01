package com.blinkfox.patterns.proxy.dynamic;

import com.blinkfox.patterns.proxy.ISubject;
import com.blinkfox.patterns.proxy.RealSubject;
import java.lang.reflect.Proxy;

/**
 * 动态代理的客户端场景测试类
 * Created by blinkfox on 2017/1/1.
 */
public class DynamicProxyClient {

    public static void main(String[] args) {
        ISubject subject = new RealSubject();
        ISubject proxySubject = (ISubject) Proxy.newProxyInstance(ISubject.class.getClassLoader(),
                new Class[] {ISubject.class}, new DynamicHandler(subject));
        proxySubject.request();
    }

}