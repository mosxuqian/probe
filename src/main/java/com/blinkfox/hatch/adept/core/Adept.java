package com.blinkfox.hatch.adept.core;

import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.hatch.adept.exception.NoDataSourceException;
import com.blinkfox.hatch.adept.exception.NullConnectionException;
import com.blinkfox.hatch.adept.helpers.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
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

    /* ResultSet结果集 */
    private ResultSet rs;

    /**
     * 私有构造方法.
     */
    private Adept() {
        super();
    }

    /**
     * rs的getter方法.
     * @return ResultSet实例.
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * rs的setter方法.
     * @param rs ResultSet实例.
     */
    private void setRs(ResultSet rs) {
        this.rs = rs;
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
     * 创建新的Adept实例.
     * @return Adept实例.
     */
    public static Adept newInstance() {
        return new Adept();
    }

    /**
     * 快速开始，即创建Adept实例并从数据源中获取数据库连接.
     * @return Adept实例.
     */
    public static Adept quickStart() {
        Adept adept = new Adept();
        conn = JdbcHelper.getConnection(getDataSource());
        if (conn == null) {
            throw new NullConnectionException("数据库连接Connection为null");
        }
        return adept;
    }

    /**
     * 执行数据库查询.
     * @param sql sql语句
     * @param params 不定参数
     * @return ResultSet实例
     */
    public Adept query(String sql , Object... params) {
        if (sql == null || sql.length() == 0) {
            JdbcHelper.close(conn);
            return this;
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = JdbcHelper.getPreparedStatement(conn, sql, params);
            this.setRs(pstmt.executeQuery());
        } catch (SQLException e) {
            Logger.error(e, "执行SQL语句失败！");
        } finally {
            JdbcHelper.close(pstmt);
        }
        return this;
    }

}