package com.blinkfox.hatch.adept.test;

import com.blinkfox.hatch.adept.config.AbstractAdeptConfig;
import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.utils.others.PropHelper;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;

/**
 * 我的Adept配置类.
 * Created by blinkfox on 2017/5/30.
 */
public class MyAdeptConfig extends AbstractAdeptConfig {

    /**
     * 配置数据库连接池.
     */
    @Override
    public void configDataSource(ConfigInfo info) {
        Properties props = PropHelper.INSTANCE.loadPropFile("props/config.properties");
        HikariDataSource hds = info.useDefaultDataSource(props.getProperty("driver"), props.getProperty("url"),
                props.getProperty("username"), props.getProperty("password"));
        hds.setMaximumPoolSize(20);
    }

}