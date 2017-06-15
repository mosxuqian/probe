package com.blinkfox.hatch.adept.core.results;

import com.blinkfox.hatch.adept.exception.ResultsTransformException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * 将'ResultSet'结果集的第一行数据转换为'Java Bean'的处理器实现.
 * Created by blinkfox on 2017/6/15.
 */
public class BeanHandler<T> implements ResultsHandler<T> {

    /**
     * 创建新的BeanHandler实例.
     * @param b bean泛型
     * @return BeanHandler实例
     */
    public static <B> BeanHandler newInstance(B b) {
        return new BeanHandler<B>();
    }

    /**
     * 将'ResultSet'结果集的第一行数据转换为'Java Bean'.
     * @param rs ResultSet实例
     * @param otherParams 其他参数
     * @return 泛型T的实例
     */
    @Override
    public T transform(ResultSet rs, Object... otherParams) {
        if (rs == null) {
            return null;
        }

        // 遍历Resultset和元数据，将第一行各列的数据存到LinkedHashMap中
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next() && rs.first()) {
                for (int i = 0, cols = rsmd.getColumnCount(); i < cols; i++)  {
                    // map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                }
            }
        } catch (Exception e) {
            throw new ResultsTransformException("将'ResultSet'结果集转换为'Java Bean'出错!", e);
        }

        return null;
    }

}