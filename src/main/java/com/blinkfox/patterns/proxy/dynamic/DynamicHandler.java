package com.blinkfox.patterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理的调用处理器
 * Created by blinkfox on 2017/1/1.
 */
public class DynamicHandler implements InvocationHandler {

    private Object obj;

    /**
     * 构造方法
     * @param obj
     */
    public DynamicHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * 动态代理的调用方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用开始执行的方法");
        Object result = method.invoke(this.obj, args);
        System.out.println("调用结束执行的方法");
        return result;
    }

}