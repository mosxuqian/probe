package com.blinkfox.patterns.proxy.normal;

import com.blinkfox.patterns.proxy.ISubject;
import com.blinkfox.patterns.proxy.RealSubject;

/**
 * 代理模式客户端场景类
 * Created by blinkfox on 2017/1/1.
 */
public class ProxyClient {

    public static void main(String[] args) {
        ISubject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.request();
    }

}