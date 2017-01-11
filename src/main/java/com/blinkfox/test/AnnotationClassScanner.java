package com.blinkfox.test;

import com.blinkfox.utils.Log;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import eu.infomas.annotation.AnnotationDetector;
import eu.infomas.annotation.AnnotationDetector.Reporter;
import eu.infomas.annotation.AnnotationDetector.TypeReporter;

/**
 * 注解扫描工具类的工具类
 * Created by blinkfox on 2017/1/4.
 */
public final class AnnotationClassScanner {

    private static final Log log = Log.get(AnnotationClassScanner.class);

    /**
     * 私有构造器
     */
    private AnnotationClassScanner() {
    }

    /**
     * 扫描类注解.
     *
     * @param annotationClass
     * @param packages
     * @return
     */
    public static Set<Class<?>> scan(final Class<? extends Annotation> annotationClass,
            String... packages) {
        final Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        final Reporter reporter = new TypeReporter() {

            @SuppressWarnings("unchecked")
            @Override
            public Class<? extends Annotation>[] annotations() {
                return new Class[] { annotationClass };
            }

            @Override
            public void reportTypeAnnotation(
                    Class<? extends Annotation> annotation, String className) {
                loadClass(classes, className);
            }

        };
        return startScan(classes, reporter, packages);
    }

    /**
     * 开始扫描
     * @param classes
     * @param reporter
     * @param packageNames
     * @return
     */
    private static Set<Class<?>> startScan(final Set<Class<?>> classes,
            final Reporter reporter, String... packageNames) {
        final AnnotationDetector cf = new AnnotationDetector(reporter);
        try {
            if (packageNames.length == 0) {
                // 解决在web容器下扫描不到类的问题.
                URL url = Thread.currentThread().getContextClassLoader()
                        .getResource("");
                File file = new File(url.getPath());
                File[] files = file.listFiles(new FileFilter() {

                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isDirectory() && !pathname.isHidden();
                    }
                });
                List<String> fileNames = new ArrayList<String>();
                for (File one : files) {
                    fileNames.add(one.getName());
                }
                log.info("scan path:{}" + fileNames.toString());
                cf.detect(toStringArray(fileNames));
                // FIXME 这里扫描全部可能会有性能问题
                // XXX 在java项目中可以扫描到jar文件中的类，在web项目中不行.
                cf.detect();
            } else {
                cf.detect(packageNames);
            }
        } catch (IOException e) {
            log.error("scan package error packages:" + Arrays.toString(packageNames), e);

        }
        return classes;
    }

    /**
     * 加载类.
     * @param classes
     * @param className
     */
    private static void loadClass(final Set<Class<?>> classes, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            classes.add(clazz);
        } catch (ClassNotFoundException e) {
            log.error("load class error . className:" + className, e);
        }
    }

    /**
     * 转换为String数组
     *
     * @param array
     * @return
     */
    public static String[] toStringArray(List<String> array) {
        return array.toArray(new String[] {});
    }

}