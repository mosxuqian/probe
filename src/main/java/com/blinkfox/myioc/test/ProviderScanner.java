package com.blinkfox.myioc.test;

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

    static class ProviderReporter implements AnnotationDetector.TypeReporter {

        private final Class<? extends Annotation>[] annotations;

        /**
         * 构造方法
         * @param annotations
         */
        ProviderReporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        @Override
        public void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
            System.out.println("注解名称:" + annotation.getName() + ",注解到的类:" + className);
        }

        @Override
        public Class<? extends Annotation>[] annotations() {
            return annotations;
        }

    }

    public static void main(String[] args) {
        ProviderReporter providerReporter = new ProviderReporter(Provider.class);
        AnnotationDetector detector = new AnnotationDetector(providerReporter);

        try {
            detector.detect("com.blinkfox");
        } catch (IOException e) {
            log.error("在com.blinkfox包下扫描@Provider注解出错！", e);
        }
    }

}