package com.blinkfox.myioc.core;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.consts.Scope;
import com.blinkfox.myioc.tools.ClassHelper;
import com.blinkfox.myioc.tools.StringHelper;
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
     * 用来将注解和类信息存储起来
     */
    private class Reporter implements AnnotationDetector.TypeReporter, AnnotationDetector.FieldReporter {

        private final Class<? extends Annotation>[] annotations;

        // 存放提供者及需要注入的依赖对象信息集合的Map,其中 key是全路径类名className,value是ProviderInfo对象
        private final Map<String, ProviderInfo> providerInfoMap = new HashMap<String, ProviderInfo>();

        // 用来存放Provider的ID和全路径类名的一一对应关系信息的Map，key是ProviderId，value是对应类的Class
        private final Map<String, Class> idClsMap = new HashMap<String, Class>();

        /**
         * AnnotationReporter构造方法
         * @param annotations 各注解的class
         */
        Reporter(Class<? extends Annotation>... annotations) {
            this.annotations = annotations;
        }

        /**
         * 根据全路径类名的className,构建出该类依赖注入需要的提供者信息，即ProviderInfo对象
         * @param clsName 全路径类名
         * @return 提供者信息
         */
        private ProviderInfo initProviderInfoByClsName(String clsName) {
            // 获取该类所在的类class和注解相关信息，并将其保存到providerInfo对象中
            Class cls = ClassHelper.getClass(clsName);
            String providerId;
            Scope scope = Scope.SINGLETON;
            if (cls.isAnnotationPresent(Provider.class)) {
                Provider provider = (Provider) cls.getAnnotation(Provider.class);
                // 依赖诸如提供者的ID，如果为空，则默认将类名转换成驼峰式命名作为ID
                providerId = provider.value();
                providerId = "".equals(providerId.trim()) ? StringHelper.getCamelByClsName(clsName) : providerId;
                scope = provider.scope();
            } else {
                providerId = StringHelper.getCamelByClsName(clsName);
            }

            // 将Provider的ID和全路径类名存到 idClsMap 中
            idClsMap.put(providerId, cls);

            // 初始化ProviderInfo信息，包括class及其对应的id、scope等，然后将其存放到map中
            return ProviderInfo.newInstance().setId(providerId).setCls(cls).setScope(scope);
        }

        /**
         * 类、接口或枚举的注解所在类的信息
         * @param annotation 注解的class
         * @param clsName 注解的全路径类名
         */
        @Override
        public void reportTypeAnnotation(Class<? extends Annotation> annotation, String clsName) {
            if (!providerInfoMap.containsKey(clsName)) {
                providerInfoMap.put(clsName, this.initProviderInfoByClsName(clsName));
            }
        }

        /**
         * 字段上的注解的类或字段的信息
         * @param annotation 注解class
         * @param clsName 类名
         * @param fieldName 字段名
         */
        @Override
        public void reportFieldAnnotation(Class<? extends Annotation> annotation, String clsName, String fieldName) {
            // 先判断map中是否包含该类的ProviderInfo信息，如果有则获取到fields，设置该字段到fields中
            ProviderInfo providerInfo = providerInfoMap.containsKey(clsName) ? providerInfoMap.get(clsName) :
                    this.initProviderInfoByClsName(clsName);
            List<String> fields = providerInfo.getFields();
            fields.add(fieldName);
            providerInfo.setFields(fields);
            providerInfoMap.put(clsName, providerInfo);
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
    public Map<String, ProviderInfo> getProviderInfoMaps(String... packages) {
        Reporter reporter = new Reporter(Provider.class, Injection.class);
        AnnotationDetector detector = new AnnotationDetector(reporter);

        try {
            // 扫描各包下的注解
            detector.detect(packages);
        } catch (IOException e) {
            log.error("扫描依赖注入注解出错！扫描的包有：" + Arrays.toString(packages), e);
        }

        // 通过两个map中的数据，填充ProviderInfo中的injects数据，即某个类中依赖注入需要的Class集合
        Map<String, Class> idClsMap = reporter.idClsMap;
        Map<String, ProviderInfo> providerInfoMap = reporter.providerInfoMap;
        for (Map.Entry<String, ProviderInfo> entry: providerInfoMap.entrySet()) {
            ProviderInfo providerInfo = entry.getValue();
            List<String> fields = providerInfo.getFields();
            List<Class> injects = providerInfo.getInjects();
            for (String field: fields) {
                // 先判断是否包含该注入的ID，如果没有则抛出异常，否则将需要注入的class加到ProviderInfo中的 injects 集合中
                if (!idClsMap.containsKey(field)) {
                    throw new RuntimeException("未找到需要注入ID为" + field + "所依赖的类！");
                }
                injects.add(idClsMap.get(field));
            }
            providerInfo.setInjects(injects);
        }

        return reporter.providerInfoMap;
    }

}