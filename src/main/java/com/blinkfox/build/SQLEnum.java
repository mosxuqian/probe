package com.blinkfox.build;

/**
 * Created by blinkfox on 16-5-13.
 */
public enum SQLEnum {

    SELECT("SELECT"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    INSERT_INTO("INSERT_INTO"),
    FROM("FROM"),
    WHERE("WHERE"),
    GROUP_BY("GROUP_BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER_BY"),
    AS("AS"),
    LEFT_JOIN("LEFT_JOIN"),
    RIGHT_JOIN("RIGHT_JOIN"),
    FULL_JOIN("FULL_JOIN"),
    ON("ON"),
    SPACE(" ");

    public String c;

    SQLEnum(String c) {
        this.c = c;
    }
}