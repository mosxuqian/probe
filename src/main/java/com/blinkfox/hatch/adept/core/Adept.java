package com.blinkfox.hatch.adept.core;

import com.blinkfox.hatch.adept.config.ConfigInfo;
import com.blinkfox.hatch.adept.exception.NoDataSourceException;
import com.blinkfox.hatch.adept.exception.NullConnectionException;
import com.blinkfox.hatch.adept.helpers.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
     * 得到map集合结果集并关闭资源.
     * @return maps集合
     */
    public List<Map<String, Object>> end() {
        List<Map<String, Object>> maps = this.toMapList();
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
        if (sql == null || sql.length() == 0) {
            JdbcHelper.close(conn);
            return this;
        }

        try {
            pstmt = JdbcHelper.getPreparedStatement(conn, sql, params);
            this.setRs(pstmt.executeQuery());
        } catch (SQLException e) {
            Logger.error(e, "执行SQL语句失败！");
        }
        return this;
    }

    /**
     * 将查询结果转换为map集合类型,即`List[Map[String, Object]]` 类型.
     * @return maps集合
     */
    private List<Map<String, Object>> toMapList() {
        if (rs == null) {
            return null;
        }

        // 遍历Resultset和元数据，将每一行各列的数据存到Map中，然后将各行数据add到List集合中
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 0; i < cols; i++)  {
                    map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                }
                maps.add(map);
            }
        } catch (SQLException e) {
            Logger.error(e, "遍历获取ResultSet数据异常！");
        }
        return maps;
    }

}