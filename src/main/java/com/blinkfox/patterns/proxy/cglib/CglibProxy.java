package com.blinkfox.patterns.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * Cglib代理类
 * Created by blinkfox on 2017/1/2.
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    /**
     * 获取代理类
     * @return
     */
    public Object getProxy(Class cls) {
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截方法
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args,
            MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("后置代理");
        return result;
    }

}