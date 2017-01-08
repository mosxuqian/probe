package com.blinkfox.myioc.test;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.utils.Log;
import eu.infomas.annotation.AnnotationDetector;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * 需要注入的实例的注解扫描类
 * Created by blinkfox on 2017/1/8.
 */
public class InjectionScanner {

    private static final Log log = Log.get(ProviderScanner.class);

    static class InjectionReporter implements AnnotationDetector.FieldReporter {

        private final Class<? extends Annotation>[] annotations;

        InjectionReporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        @Override
        public void reportFieldAnnotation(Class<? extends Annotation> annotation, String className, String fieldName) {
            System.out.println("注解名称:" + annotation.getName() + ",注解到的类:" + className + ",注解的字段:" + fieldName);
        }

        @Override
        public Class<? extends Annotation>[] annotations() {
            return annotations;
        }
    }

    public static void main(String[] args) {
        InjectionScanner.InjectionReporter injectionReporter = new InjectionScanner.InjectionReporter(Injection.class);
        AnnotationDetector detector = new AnnotationDetector(injectionReporter);

        try {
            detector.detect("com.blinkfox");
        } catch (IOException e) {
            log.error("在com.blinkfox包下扫描@Injection注解出错！", e);
        }
    }

}