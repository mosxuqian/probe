package com.blinkfox.hatch.adept.core.columns;

import com.blinkfox.hatch.adept.core.columns.impl.PrimitiveHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 列值处理器的链类.
 * Created by blinkfox on 2017/6/17.
 */
public final class ColumnHandlerChain {

    /**
     * 私有构造方法.
     */
    private ColumnHandlerChain() {
        super();
    }

    public static ColumnHandlerChain newInstance() {
        return new ColumnHandlerChain();
    }

    /**
     * 根据JavaBean属性类型名称得到ResultSet对应序号列的值.
     * @param rs ResultSet实例
     * @param colNum 结果集列序号
     * @param propType 属性类型
     * @return 字段值
     */
    public Object getColumnValue(ResultSet rs, int colNum, Class<?> propType) throws SQLException {
        if (propType.isPrimitive()) {
            return PrimitiveHandler.newInstance().getColumnValue(rs, colNum, propType);
        }

        return rs.getObject(colNum);
    }

}