package com.blinkfox.hatch.adept.core.results;

import com.blinkfox.hatch.adept.exception.ResultsTransformException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 将'ResultSet'结果集的第一行数据转换为'有序Map'的处理器实现.
 * Created by blinkfox on 2017/6/11.
 */
public class MapHandler implements ResultsHandler<Map<String, Object>> {

    /**
     * 私有构造方法.
     */
    private MapHandler() {
        super();
    }

    public static MapHandler newInstance() {
        return new MapHandler();
    }

    /**
     * 将'ResultSet'结果集的第一行数据转换为'有序Map'.
     * @param rs ResultSet实例
     * @param otherParams 其他参数
     * @return Map
     */
    @Override
    public Map<String, Object> transform(ResultSet rs, Object... otherParams) {
        if (rs == null) {
            return null;
        }

        // 遍历Resultset和元数据，将第一行各列的数据存到LinkedHashMap中
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                if (rs.first()) {
                    for (int i = 0, cols = rsmd.getColumnCount(); i < cols; i++)  {
                        map.put(rsmd.getColumnName(i + 1), rs.getObject(i + 1));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new ResultsTransformException("将'ResultSet'结果集转换为'map的List集合'出错!", e);
        }

        return map;
    }

}