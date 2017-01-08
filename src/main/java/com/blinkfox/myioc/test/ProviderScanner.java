package com.blinkfox.myioc.test;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.utils.Log;
import eu.infomas.annotation.AnnotationDetector;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Provider注解扫描类
 * Created by blinkfox on 2017/1/8.
 */
public class ProviderScanner {

    private static final Log log = Log.get(ProviderScanner.class);

    static class ProviderReporter implements AnnotationDetector.TypeReporter, AnnotationDetector.FieldReporter {

        private final Class<? extends Annotation>[] annotations;

        /**
         * 构造方法
         * @param annotations
         */
        ProviderReporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        @Override
        public Class<? extends Annotation>[] annotations() {
            return annotations;
        }

        @Override
        public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
            System.out.println("注解名称:" + annotation.getName() + ",注解到的类:" + className);
        }

        @Override
        public void reportFieldAnnotation(Class<? extends Annotation> annotation, String className, String fieldName) {
            System.out.println("注解名称:" + annotation.getName() + ",注解到的类:" + className + ",注解的字段:" + fieldName);
        }

    }

    public static void main(String[] args) {
        ProviderReporter providerReporter = new ProviderReporter(Provider.class, Injection.class);
        AnnotationDetector detector = new AnnotationDetector(providerReporter);

        try {
            detector.detect("com.blinkfox");
        } catch (IOException e) {
            log.error("在com.blinkfox包下扫描@Provider和@Injection注解出错！", e);
        }
    }

}