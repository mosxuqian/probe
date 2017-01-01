package com.blinkfox.test.reflect;

/**
 * Java反射和不用反射的效率对比
 * Created by blinkfox on 2017/1/1.
 */
public class ReflectPerforTest {

    /**
     * 通常方式
     * @throws Exception
     */
    public static void doRegular() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            A a = new A();
            a.doSomeThing();
        }
        System.out.println("通常方式耗时：" + (System.currentTimeMillis() - start));
    }

    /**
     * 使用反射
     * @throws Exception
     */
    public static void doReflection() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            A a = (A) Class.forName("com.blinkfox.test.reflect.A").newInstance();
            a.doSomeThing();
        }
        System.out.println("使用反射耗时：" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) throws Exception {
        doRegular();
        doReflection();
    }

}