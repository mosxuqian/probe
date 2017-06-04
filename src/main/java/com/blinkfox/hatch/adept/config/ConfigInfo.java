package com.blinkfox.hatch.adept.config;

import javax.sql.DataSource;

/**
 * Adept封装配置信息的实体类.
 * Created by blinkfox on 2017/5/31.
 */
public final class ConfigInfo {

    /* ConfigInfo的唯一实例 */
    private static final ConfigInfo configInfo = new ConfigInfo();

    /* 数据源 */
    private DataSource dataSource;

    /**
     * 私有构造方法.
     */
    private ConfigInfo() {
        super();
    }

    /**
     * 得到ConfigInfo的唯一实例.
     * @return ConfigInfo实例
     */
    public static ConfigInfo getInstance() {
        return configInfo;
    }

    /**
     * 清除配置信息.
     */
    public void clear() {
        dataSource = null;
    }

    /* getter and setter */
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}