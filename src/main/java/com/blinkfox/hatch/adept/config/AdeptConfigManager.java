package com.blinkfox.hatch.adept.config;

import com.blinkfox.hatch.adept.exception.LoadAdeptConfigException;
import org.pmw.tinylog.Logger;

/**
 * Adept配置信息管理器类.
 * Created by blinkfox on 2017/5/30.
 */
public enum AdeptConfigManager {

    /** 唯一实例. */
    INSTANCE;

    /**
     * 初始化加载Adept的配置信息到内存缓存中.
     * @param configClass 系统中Adept的class全路径
     */
    public void initLoad(String configClass) {
        this.getAndLoadConfig(configClass);
    }

    /**
     * 获取AdeptConfig的实例.
     * @param configClass 配置类的class路径
     */
    private void getAndLoadConfig(String configClass) {
        Logger.info("Adept开始加载，Zealot配置类为:{}", configClass);
        if (configClass == null || configClass.length() == 0) {
            throw new LoadAdeptConfigException("未获取到 AdeptConfig 配置信息!");
        }

        // 根据class类名得到初始化实例
        Object adeptConfig;
        try {
            adeptConfig = Class.forName(configClass).newInstance();
        } catch (Exception e) {
            throw new LoadAdeptConfigException("初始化AdeptConfig实例失败,配置类名称为:" + configClass, e);
        }

        // 判断获取到的类是否是AbstractZealotConfig的子类，如果是，则加载xml和自定义标签
        if (adeptConfig != null && adeptConfig instanceof AbstractAdeptConfig) {
            this.load((AbstractAdeptConfig) adeptConfig);
        }
    }

    /**
     * 加载 AdeptConfig 的子类信息，并将配置信息加载到内存缓存中.
     * @param config 配置类
     */
    private void load(AbstractAdeptConfig config) {
        config.configDataSource();
        Logger.info("Adept的配置信息加载完成!");
    }

}