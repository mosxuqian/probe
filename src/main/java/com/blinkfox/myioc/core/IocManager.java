package com.blinkfox.myioc.core;

import com.blinkfox.myioc.bean.Nil;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.consts.Scope;
import com.blinkfox.myioc.tools.ClassHelper;
import com.blinkfox.utils.Log;
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
        Map<String, ProviderInfo> providerInfoMap = IocAnnoScanner.INSTANCE.getProviderInfoMaps(packages);
        for (ProviderInfo providerInfo: providerInfoMap.values()) {
            Object obj = (providerInfo.getScope() == Scope.SINGLETON) ?
                    ClassHelper.newInstance(providerInfo.getCls()) : Nil.INSTANCE;
            beanMap.put(providerInfo.getId(), obj);
        }
        return beanMap;
    }

}