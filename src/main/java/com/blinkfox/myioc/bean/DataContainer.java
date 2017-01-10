package com.blinkfox.myioc.bean;

import com.blinkfox.myioc.exceptioni.ProviderNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * 用来存放依赖注入数据的容器
 * Created by blinkfox on 2017-01-10.
 */
public class DataContainer {

    // 存放提供者及需要注入的依赖对象信息集合的Map,其中key是全路径类名className,value是ProviderInfo对象
    private Map<String, ProviderInfo> providerInfoMap;

    // 用来存放Provider的ID和全路径类名的一一对应关系信息的Map，key是ProviderId，value是对应类的Class
    private Map<String, Class> idClsMap;

    // 初始化的唯一实例
    private static final DataContainer container = new DataContainer();

    /**
     * 私有构造方法
     */
    private DataContainer () {
        super();
    }

    /**
     * 获取DataContainer实例
     * @return container
     */
    public static DataContainer getInstance() {
        return container;
    }

    /**
     * 根据扫描注解的结果构造初始化的基础数据
     * @param providerInfoMap 提供依赖注入的类及其依赖元素集合的Map
     * @param idClsMap Provider的ID和全路径类名的Map
     * @return 依赖注入数据容器
     */
    public DataContainer initBaseDatas(Map<String, ProviderInfo> providerInfoMap,
            Map<String, Class> idClsMap) {
        container.setProviderInfoMap(providerInfoMap);
        container.setIdClsMap(idClsMap);
        return container;
    }

    /**
     * 检测依赖注入关系是否完整
     */
    public void validDependency() {
        for (Map.Entry<String, ProviderInfo> entry: providerInfoMap.entrySet()) {
            ProviderInfo providerInfo = entry.getValue();
            List<String> fields = providerInfo.getFields();
            for (String field: fields) {
                // 判断是否包含该注入的ID，如果没有则抛出异常
                if (!idClsMap.containsKey(field)) {
                    throw new ProviderNotFoundException("未找到需要注入ID为" + field + "所依赖的类！");
                }
            }
        }
    }

    /* 以下是 getter 和 setter 方法*/
    public Map<String, ProviderInfo> getProviderInfoMap() {
        return providerInfoMap;
    }

    public void setProviderInfoMap(Map<String, ProviderInfo> providerInfoMap) {
        this.providerInfoMap = providerInfoMap;
    }

    public Map<String, Class> getIdClsMap() {
        return idClsMap;
    }

    public void setIdClsMap(Map<String, Class> idClsMap) {
        this.idClsMap = idClsMap;
    }

}