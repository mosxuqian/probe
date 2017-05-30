package com.blinkfox.hatch.adept.test;

import com.blinkfox.hatch.adept.config.AbstractAdeptConfig;
import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.utils.others.PropHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;

/**
 * 我的Adept配置类.
 * Created by blinkfox on 2017/5/30.
 */
public class MyAdeptConfig extends AbstractAdeptConfig {

    /**
     * 配置自己的数据库连接池.
     */
    @Override
    public void configDataSource(ConfigInfo info) {
        Properties props = PropHelper.INSTANCE.loadPropFile("props/config.properties");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(props.getProperty("driver"));
        config.setJdbcUrl(props.getProperty("url"));
        config.setUsername(props.getProperty("username"));
        config.setPassword(props.getProperty("password"));
        info.setDataSource(new HikariDataSource(config));
    }

}