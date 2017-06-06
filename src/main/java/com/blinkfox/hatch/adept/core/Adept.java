package com.blinkfox.hatch.adept.core;

import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.hatch.adept.exception.NoDataSourceException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.blinkfox.hatch.adept.exception.NullConnectionException;
import org.pmw.tinylog.Logger;

/**
 * Adept核心调用类.
 * Created by blinkfox on 2017/6/5.
 */
public final class Adept {

    /* 配置信息 */
    private static final ConfigInfo configInfo = ConfigInfo.getInstance();

    /* 数据库连接 */
    private static Connection conn;

    /**
     * 私有构造方法.
     */
    private Adept() {
        super();
    }

    /**
     * 获取数据源.
     */
    public static DataSource getDataSource() {
        DataSource ds = configInfo.getDataSource();
        if (ds == null) {
            throw new NoDataSourceException("未找到数据源信息!");
        }
        return ds;
    }

    /**
     * 从数据源（连接池）中获取数据库连接.
     * @return Connection实例.
     */
    public static Connection getConnection() {
        return getDataSourceConnection(getDataSource());
    }

    /**
     * 从数据源（连接池）中获取数据库连接.
     * @return Connection实例
     */
    public static Connection getConnection(DataSource ds) {
        if (ds == null) {
            throw new NoDataSourceException("未传递有效的数据源参数实例!");
        }
        return getDataSourceConnection(ds);
    }

    /**
     * 从不为空的数据源中获取数据库连接.
     * @param ds 数据源
     * @return Connection实例
     */
    private static Connection getDataSourceConnection(DataSource ds) {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            Logger.error(e, "从数据源（连接池）中获取数据库连接失败.");
            return null;
        }
    }

    /**
     * 从数据源中获取连接执行数据库相关操作.
     * @return Adept实例.
     */
    public static Adept newInstance() {
        Adept adept = new Adept();
        conn = getConnection();
        if (conn == null) {
            throw new NullConnectionException("数据库连接Connection为null");
        }
        return adept;
    }

    /**
     * 执行数据库查询.
     * @param sql sql语句
     * @param params 不定参数
     */
    public void query(String sql , Object... params) {
        // TODO
    }

}