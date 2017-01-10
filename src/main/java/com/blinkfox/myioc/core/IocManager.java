package com.blinkfox.myioc.core;

import com.blinkfox.myioc.annotation.Injection;
import com.blinkfox.myioc.bean.DataContainer;
import com.blinkfox.myioc.bean.Nil;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.consts.Scope;
import com.blinkfox.myioc.tools.ClassHelper;
import com.blinkfox.utils.Log;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IOC管理器
 * Created by blinkfox on 2017/1/8.
 */
public enum IocManager {

    INSTANCE; // 实例

    private static final Log log = Log.get(IocManager.class);

    // 用来存放key为注入id和value是实例bean的Map
    private static final Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    /**
     * 私有构造方法
     */
    private IocManager() {

    }

    /**
     * 初始化依赖注入所有提供者的注入id和实例bean的Map
     * @param packages 多个不定参数的包路径名
     * @return key为注入id和value是实例bean的Map
     */
    public Map<String, Object> initProviderBeanMap(String... packages) {
        DataContainer container = IocAnnoScanner.INSTANCE.getProviderInfoMaps(packages);
        Map<String, ProviderInfo> providerInfoMap = container.getProviderInfoMap();
        for (ProviderInfo providerInfo: providerInfoMap.values()) {
            if (providerInfo.getScope() == Scope.SINGLETON) {
                Class cls = providerInfo.getCls();
                Object obj = ClassHelper.newInstance(cls);
                List<String> fields = providerInfo.getFields();
                Field[] declarFelds = cls.getDeclaredFields();
                for (Field df: declarFelds) {
                    if (df.isAnnotationPresent(Injection.class)) {
                        df.setAccessible(true);
                        try {
                            df.set(obj, null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Object obj = (providerInfo.getScope() == Scope.SINGLETON) ?
                    ClassHelper.newInstance(providerInfo.getCls()) : Nil.INSTANCE;
            beanMap.put(providerInfo.getId(), obj);
        }

        return beanMap;
    }

    /**
     * 根据ProviderInfo中的信息生成对应类的实例，如果是原型，则不生成对象，而赋值一个空类型
     * @param providerInfo 依赖注入提供者信息
     * @return 实例
     */
    private Object newInstance(ProviderInfo providerInfo) {
        return (providerInfo.getScope() == Scope.SINGLETON) ?
                ClassHelper.newInstance(providerInfo.getCls()) : Nil.INSTANCE;
    }

}