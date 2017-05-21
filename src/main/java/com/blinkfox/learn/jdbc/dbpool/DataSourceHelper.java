package com.blinkfox.learn.jdbc.dbpool;

import com.blinkfox.utils.others.PropHelper;
import java.sql.Connection;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.pmw.tinylog.Logger;

/**
 * DataSourceHelper.
 * Created by blinkfox on 2017/5/21.
 */
public final class DataSourceHelper {

    private static BasicDataSource ds = null;

    /**
     * 私有构造方法.
     */
    private DataSourceHelper() {
        super();
    }

    static {
        ds = new BasicDataSource();
        Properties props = PropHelper.INSTANCE.loadPropFile("props/config.properties");

        // 设置基本连接属性
        ds.setDriverClassName(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));

        ds.setMaxTotal(Integer.parseInt(props.getProperty("maxTotal", "20")));
        ds.setMaxIdle(Integer.parseInt(props.getProperty("maxIdle", "10")));
        ds.setMinIdle(Integer.parseInt(props.getProperty("minIdle", "5")));
        ds.setInitialSize(Integer.parseInt(props.getProperty("initSize", "10")));
    }

    /**
     * 获取数据库连接.
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (Exception e) {
            Logger.error(e, "从数据库连接池中获取数据库连接出错！");
            return null;
        }
    }

}