package com.blinkfox.test.reflect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * CostTime 方法执行耗时的代理方法
 * Created by blinkfox on 2017-01-04.
 */
public class CostTimeProxy implements MethodInterceptor {

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
     * 拦截方法，判断是否有 @CostTime 的注解，如果有则拦截执行
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 判断该方法上是否有 CostTime 注解
        if (!method.isAnnotationPresent(CostTime.class)) {
            return methodProxy.invokeSuper(o, args);
        }
        // 获取注解信息
        CostTime costTime = method.getAnnotation(CostTime.class);
        long limitTime = costTime.value();

        // 记录方法执行前后的耗时时间，并做差，判断是否需要打印方法执行耗时
        long startTime = System.currentTimeMillis();
        Object result = methodProxy.invokeSuper(o, args);
        long diffTime = System.currentTimeMillis() - startTime;
        if (limitTime <= 0 || (diffTime >= limitTime)) {
            String methodName = method.getName();
            // 打印耗时的信息
            System.out.println("-----通过注解监控" + methodName + " 方法的执行耗时为:" + diffTime + " ms");
        }
        return null;
    }

}