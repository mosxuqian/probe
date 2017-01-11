package com.blinkfox.myioc.core;

import com.blinkfox.myioc.bean.DataContainer;
import java.util.Map;

/**
 * 依赖注入应用操作bean的上下文
 * Created by blinkfox on 2017-01-11.
 */
public class AppContext {

    /**
     * 私有构造方法
     */
    private AppContext() {
        super();
    }

    /**
     * 初始化加载注解信息
     * @param packages 需要加载的包路径
     */
    public static void init(String... packages) {
        IocManager.INSTANCE.initProviderBeanMap(packages);
    }

    /**
     * 根据Bean的ID获取Bean的实例
     * @param beanId bean提供者的ID
     * @return bean的实例
     */
    public static Object getBean(String beanId) {
        Map<String, Object> beanMap = DataContainer.getInstance().getBeanMap();
        return beanMap.get(beanId);
    }

}