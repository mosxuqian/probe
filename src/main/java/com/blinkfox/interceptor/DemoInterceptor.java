package com.blinkfox.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 测试的拦截器
 * Created by blinkfox on 16/7/27.
 */
public class DemoInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("Before method invoking");
        inv.invoke();
        System.out.println("Before method invoking");
    }

}