package com.blinkfox.myioc.core;

import com.blinkfox.myioc.bean.DataContainer;
import com.blinkfox.myioc.bean.ProviderInfo;
import com.blinkfox.myioc.tools.ClassStrategy;
import com.blinkfox.myioc.tools.ReflectHelper;
import com.blinkfox.utils.Log;
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
    public void initProviderBeanMap(String... packages) {
        // 先获取依赖注入需要的全路径类名、提供者信息、依赖注入的id和对应的class等基础信息
        DataContainer container = IocAnnoScanner.INSTANCE.getProviderInfoMaps(packages);
        Map<String, ProviderInfo> providerInfoMap = container.getProviderInfoMap();
        Map<String, Class> idClsMap = container.getIdClsMap();

        // 遍历对每个提供者类及其依赖注入属性生成实例
        Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();
        for (ProviderInfo providerInfo: providerInfoMap.values()) {
            Object obj = injectProviderFields(providerInfo, providerInfoMap, idClsMap);
            beanMap.put(providerInfo.getId(), obj);
        }

        // 将Map存放到全局的数据容器中供获取
        container.setBeanMap(beanMap);
    }

    /**
     * 对某个Provider的所有需要依赖注入的属性做注入功能，递归实例化每个类和属性的实例
     * @param providerInfo 提供者信息实例
     * @param providerInfoMap 提供依赖注入的类及其依赖元素集合的Map
     * @param idClsMap Provider的ID和全路径类名的Map
     * @return 该提供者对应的实例
     */
    private Object injectProviderFields(ProviderInfo providerInfo,
            Map<String, ProviderInfo> providerInfoMap, Map<String, Class> idClsMap) {
        // 遍历对每个提供者类及其依赖注入属性生成实例
        Class cls = providerInfo.getCls();
        Object obj = ClassStrategy.SINGLETON.getInstance(cls);
        List<String> fields = providerInfo.getFields();
        for (String fieldName: fields) {
            Class injectCls = idClsMap.get(fieldName);
            ProviderInfo injectProviderInfo = providerInfoMap.get(injectCls.getName());
            Object injectObj = injectProviderFields(injectProviderInfo, providerInfoMap, idClsMap);
            ReflectHelper.setFieldValue(cls, obj, injectObj, fieldName);
        }
        return obj;
    }

}