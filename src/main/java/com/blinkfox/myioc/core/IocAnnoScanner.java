package com.blinkfox.myioc.core;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.utils.Log;
import eu.infomas.annotation.AnnotationDetector;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 控制反转注解扫描单例类
 * Created by blinkfox on 2017/1/8.
 */
public enum IocAnnoScanner {

    INSTANCE; // 枚举单例

    private static final Log log = Log.get(IocAnnoScanner.class);

    /**
     * 私有的构造方法
     */
    private IocAnnoScanner() {

    }

    /**
     * 扫描依赖注入的注解及其所在类、字段等信息的内部类
     */
    private class Reporter implements AnnotationDetector.TypeReporter, AnnotationDetector.FieldReporter {

        private final Class<? extends Annotation>[] annotations;

        // 存放提供者及需要注入的依赖对象集合的Map
        private final Map<String, List<String>> providerInjectionMap = new HashMap<String, List<String>>();

        /**
         * AnnotationReporter构造方法
         * @param annotations 各注解的class
         */
        Reporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        /**
         * 类、接口或枚举的注解所在类的信息
         * @param annotation 注解的class
         * @param className 注解的类名
         */
        @Override
        public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
            if (providerInjectionMap.containsKey(className)) {
                log.info("-----已经包含className的注解了，className:" + className + ",:" + annotation.getName());
            } else {
                providerInjectionMap.put(className, new ArrayList<String>());
                log.info("注解名称:" + annotation.getName() + ",注解到的类:" + className);
            }
        }

        /**
         * 字段上的注解的类或字段的信息
         * @param annotation 注解class
         * @param className 类名
         * @param fieldName 字段名
         */
        @Override
        public void reportFieldAnnotation(Class<? extends Annotation> annotation, String className, String fieldName) {
            List<String> injections;
            if (providerInjectionMap.containsKey(className)) {
                injections = providerInjectionMap.get(className);
                injections.add(fieldName);
            } else {
                injections = new ArrayList<String>();
                injections.add(fieldName);
            }
            providerInjectionMap.put(className, injections);
            log.info("注解名称:" + annotation.getName() + ",注解到的类:" + className + ",注解的字段:" + fieldName);
        }

        /**
         * 返回所有注解的数组
         * @return 注解数组
         */
        @Override
        public Class<? extends Annotation>[] annotations() {
            return annotations;
        }

    }

    /**
     * 获取多个包下的依赖注入Provider注解类和该类下的Injection注解依赖元素集合的Map信息
     * @param packages 多个包路径名
     * @return 提供依赖注入的类及其依赖元素集合的Map信息
     */
    public Map<String, List<String>> getProviderAndInjections(String... packages) {
        Reporter reporter = new Reporter(Provider.class, Injection.class);
        AnnotationDetector detector = new AnnotationDetector(reporter);

        try {
            // 扫描各包下的注解
            detector.detect(packages);
        } catch (IOException e) {
            log.error("扫描依赖注入注解出错！扫描的包有：" + Arrays.toString(packages), e);
        }

        return reporter.providerInjectionMap;
    }

}