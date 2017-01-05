package com.blinkfox.myioc;

import com.blinkfox.test.reflect.CostTime;
import eu.infomas.annotation.AnnotationDetector;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * 扫描注解的测试类
 * Created by blinkfox on 2017/1/4.
 */
public class AnnotationTest {

    static class CountingReporter
            implements AnnotationDetector.TypeReporter,  AnnotationDetector.MethodReporter, AnnotationDetector.FieldReporter {

        private final Class<? extends Annotation>[] annotations;
        private int typeCount;
        private int fieldCount;
        private int methodCount;

        CountingReporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        public final Class<? extends Annotation>[] annotations() {
            return annotations;
        }

        public final void reportTypeAnnotation(Class<? extends Annotation> annotation, String className) {
            ++typeCount;
            System.out.printf("%d reportTypeAnnotation on type '%s': @%s\n",
                    typeCount, className, annotation.getName());
        }

        @Override
        public void reportFieldAnnotation(Class<? extends Annotation> annotation,
                                          String className, String fieldName) {

            ++fieldCount;
            System.out.printf("%d reportFieldAnnotation on field '%s#%s': @%s\n",
                    fieldCount, className, fieldName, annotation.getName());
        }

        public final void reportMethodAnnotation(Class<? extends Annotation> annotation,
                                                 String className, String methodName) {

            ++methodCount;
            System.out.printf("%d reportMethodAnnotation on method '%s#%s': @%s\n",
                    methodCount, className, methodName, annotation.getName());
        }

        public final int getTypeCount() {
            return typeCount;
        }

        public int getFieldCount() {
            return fieldCount;
        }

        public final int getMethodCount() {
            return methodCount;
        }

    }

    public static void main(String[] args) throws IOException {
        Set<Class<?>> clsSets = AnnotationClassScanner.scan(WebServlet.class, "com.blinkfox");
        System.out.println("annotation classes sets:" + clsSets.toString());

        final CountingReporter counter = new CountingReporter(CostTime.class);
        final AnnotationDetector cf = new AnnotationDetector(counter);
        cf.detect("com.blinkfox");
    }

}