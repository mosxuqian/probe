package com.blinkfox.zealot.config;

import org.dom4j.Document;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Zealot主配置类
 * Created by blinkfox on 2016/10/30.
 */
public class ZealotConfig {

    /**
     * 所有zealots XML文档的缓存上下文map，key是资源的路径（转换成下划线分割）
     */
    protected static Map<String, String> context = new ConcurrentHashMap<String, String>();

    // 所有zealots XML文档的缓存map，key是资源的路径（转换成下划线分割），value是dom4j文档
    private static Map<String, Document> zealots = new ConcurrentHashMap<String, Document>();

    public void config() {

    }

    public static Map<String, String> getContext() {
        return context;
    }

    public static Map<String, Document> getZealots() {
        return zealots;
    }

}