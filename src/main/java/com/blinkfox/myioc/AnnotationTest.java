package com.blinkfox.myioc;

import javax.servlet.annotation.WebServlet;
import java.util.Set;

/**
 * 扫描注解的测试类
 * Created by blinkfox on 2017/1/4.
 */
public class AnnotationTest {

    public static void main(String[] args) {
        Set<Class<?>> clsSets = AnnotationClassScanner.scan(WebServlet.class, "com.blinkfox");
        System.out.println("annotation classes sets:" + clsSets.toString());
    }

}