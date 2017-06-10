package com.blinkfox.hatch.adept.core;

import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.hatch.adept.core.results.MapListHandler;
import com.blinkfox.hatch.adept.exception.NoDataSourceException;
import com.blinkfox.hatch.adept.exception.NullConnectionException;
import com.blinkfox.hatch.adept.helpers.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * Adept核心调用类.
 * Created by blinkfox on 2017/6/5.
 */
public final class Adept {

    /* 配置信息 */
    private static final ConfigInfo configInfo = ConfigInfo.getInstance();

    /* 数据库连接 */
    private static Connection conn;

    /* PreparedStatement对象引用 */
    private PreparedStatement pstmt;

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
    private Adept setRs(ResultSet rs) {
        this.rs = rs;
        return this;
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
     * 关闭数据库连接等资源.
     */
    private void closeSource() {
        JdbcHelper.close(conn, pstmt, rs);
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
     * 得到并返回'map的List集合'结果集,同时关闭资源.
     * @return maps集合
     */
    public List<Map<String, Object>> end() {
        List<Map<String, Object>> maps = MapListHandler.newInstance().transform(rs);
        this.closeSource();
        return maps;
    }

    /**
     * 执行数据库查询.
     * @param sql sql语句
     * @param params 不定参数
     * @return ResultSet实例
     */
    public Adept query(String sql , Object... params) {
        // SQL为空则关闭连接，直接返回Adept实例.
        if (sql == null || sql.length() == 0) {
            JdbcHelper.close(conn);
            return this;
        }

        // 根据数据库连接、SQL语句及参数得到PreparedStatement实例，然后再得到ResultSet实例.
        pstmt = JdbcHelper.getPreparedStatement(conn, sql, params);
        return this.setRs(JdbcHelper.getQueryResultSet(pstmt));
    }

}