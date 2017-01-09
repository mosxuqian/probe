package com.blinkfox.myioc.core;

import com.blinkfox.myioc.annotation.Provider;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.tools.StringHelper;
import com.blinkfox.utils.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * IOC管理器
 * Created by blinkfox on 2017/1/8.
 */
public enum IocManager {

    INSTANCE;

    private static final Log log = Log.get(IocManager.class);

    /**
     * 初始化依赖注入所有提供者的信息
     * @param packages 多个不定参数的包路径名
     * @return 提供者信息的集合
     */
    public List<ProviderInfo> initProviderInfo(String... packages) {
        Map<String, List<String>> iocMap = IocAnnoScanner.INSTANCE.getProviderAndInjections(packages);
        List<ProviderInfo> providerInfos = new ArrayList<ProviderInfo>();
        for (Map.Entry<String, List<String>> entry: iocMap.entrySet()) {
            String className = entry.getKey();
            List<String> fields = entry.getValue();

            // 获取要注入类的class
            Class cls = null;
            try {
                cls = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("未找到名为" + className + "的类!");
            }

            // 判断是否有@Provider注解，如果没有则默认该实例也提供,且注入的Id为该类名的驼峰式命名
            boolean isProvider = cls.isAnnotationPresent(Provider.class);
            ProviderInfo providerInfo = ProviderInfo.newInstance();
            if (isProvider) {
                Provider provider = (Provider) cls.getAnnotation(Provider.class);
                String providerId = provider.value();
                providerId = "".equals(providerId) ? StringHelper.getCamelByClsName(className) : providerId;
                providerInfo.setId(providerId).setCls(cls).setScope(provider.scope());
            } else {
                // 获取默认的注入类信息，并设置其驼峰式命名的默认id和class等信息
                String providerId = StringHelper.getCamelByClsName(className);
                providerInfo.setId(providerId).setCls(cls);
            }
            providerInfos.add(providerInfo);
        }
        return providerInfos;
    }

}