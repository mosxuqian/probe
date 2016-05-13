package com.blinkfox.build;

/**
 * Created by blinkfox on 16-5-13.
 */
public class Khala {

    StringBuffer sql;

    // SQL中常用关键字
    private static final String SELECT = "SELECT";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";
    private static final String INSERT_INTO = "INSERT INTO";
    private static final String FROM = "FROM";
    private static final String WHERE = "WHERE";
    private static final String GROUP_BY = "GROUP BY";
    private static final String HAVING = "HAVING";
    private static final String ORDER_BY = "ORDER BY";
    private static final String LEFT_JOIN = "LEFT JOIN";
    private static final String RIGHT_JOIN = "RIGHT JOIN";
    private static final String FULL_JOIN = "FULL JOIN";
    private static final String ON = "ON";
    private static final String SPACE = " ";

    // 可以公开的常量
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    // 获取sql字符串
    public String getSql() {
        return sql == null ? "": sql.toString();
    }

    public Khala() {
    }

    public Khala createSql() {
        sql = new StringBuffer("");
        return this;
    }

    /* 连接字符串 */
    private void concat(StringBuffer sql, String sqlKey, String ... params) {
        sql.append(SPACE).append(sqlKey).append(SPACE);
        for (String s: params) {
            sql.append(s).append(SPACE);
        }
    }

    public Khala select(String param) {
        concat(sql, SELECT, param);
        return this;
    }

    public Khala from(String param) {
        concat(sql, FROM, param);
        return this;
    }

    public Khala where(String param) {
        concat(sql, WHERE, param);
        return this;
    }

    public Khala orderBy(String ... param) {
        concat(sql, ORDER_BY, param);
        return this;
    }

    public Khala leftJoin(String param) {
        concat(sql, LEFT_JOIN, param);
        return this;
    }

    public Khala on(String param) {
        concat(sql, ON, param);
        return this;
    }

}