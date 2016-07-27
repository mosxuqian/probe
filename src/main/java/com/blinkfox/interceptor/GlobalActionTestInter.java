package com.blinkfox.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 测试的全局action拦截器
 * Created by blinkfox on 16/7/27.
 */
public class GlobalActionTestInter implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        System.out.println("全局action拦截器执行之前...");
        inv.invoke();
        System.out.println("全局action拦截器执行之后...");
    }

}