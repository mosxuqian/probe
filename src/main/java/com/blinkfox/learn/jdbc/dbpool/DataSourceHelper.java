package com.blinkfox.learn.jdbc.dbpool;

import com.blinkfox.utils.others.PropHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import org.pmw.tinylog.Logger;

/**
 * DataSourceHelper.
 * Created by blinkfox on 2017/5/21.
 */
public final class DataSourceHelper {

    private static HikariDataSource ds = null;

    /**
     * 私有构造方法.
     */
    private DataSourceHelper() {
        super();
    }

    static {
        Properties props = PropHelper.INSTANCE.loadPropFile("props/config.properties");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(props.getProperty("driver"));
        config.setJdbcUrl(props.getProperty("url"));
        config.setUsername(props.getProperty("username"));
        config.setPassword(props.getProperty("password"));
        config.setMaximumPoolSize(Integer.parseInt(props.getProperty("maxTotal", "20")));
        config.setMinimumIdle(Integer.parseInt(props.getProperty("minIdle", "5")));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    /**
     * 获取数据源.
     * @return DataSource唯一实例.
     */
    public static DataSource getDataSource() {
        return ds;
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

    /**
     * 关闭数据源.
     */
    public static void close() {
        if (ds != null) {
            ds.close();
        }
    }

}