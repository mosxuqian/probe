package com.blinkfox.patterns.proxy.cglib;

import com.blinkfox.patterns.proxy.ISubject;
import com.blinkfox.patterns.proxy.RealSubject;

/**
 * Cglib代理客户端场景类
 * Created by blinkfox on 2017/1/2.
 */
public class CglibProxyClient {

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ISubject subject = (ISubject) proxy.getProxy(RealSubject.class);
        subject.request();
    }

}