package com.blinkfox.hatch.adept.config;

import javax.sql.DataSource;

/**
 * Adept封装配置信息的实体类.
 * Created by blinkfox on 2017/5/31.
 */
public class ConfigInfo {

    /* 数据源 */
    private DataSource dataSource;

    /* getter and setter */
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}