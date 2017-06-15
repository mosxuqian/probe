package com.blinkfox.hatch.adept.core.results;

import com.blinkfox.hatch.adept.exception.ResultsTransformException;
import com.blinkfox.hatch.adept.helpers.JdbcHelper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.pmw.tinylog.Logger;

/**
 * 将'ResultSet'结果集的第一行数据转换为'Java Bean'的处理器实现.
 * Created by blinkfox on 2017/6/15.
 */
public class BeanHandler implements ResultsHandler {

    /* 需要转换为的Bean的Class */
    private Class beanClass;

    /**
     * 公有构造方法.
     * @param beanClass beanClass
     */
    public BeanHandler(Class beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * 创建新的BeanHandler实例.
     * @param beanClass beanClass
     * @return BeanHandler实例
     */
    public static BeanHandler newInstance(Class beanClass) {
        return new BeanHandler(beanClass);
    }

    /**
     * 将'ResultSet'结果集的第一行数据转换为'Java Bean'.
     * @param rs ResultSet实例
     * @param otherParams 其他参数
     * @return 泛型T的实例
     */
    @Override
    public Object transform(ResultSet rs, Object... otherParams) {
        if (rs == null || this.beanClass == null) {
            return null;
        }

        // 遍历Resultset和元数据，将第一行各列的数据存到'Java Bean'中
        Object beanObj = null;
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.beanClass);
            PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next() && rs.first()) {
                beanObj = beanClass.newInstance();

                for (int i = 0, cols = rsmd.getColumnCount(); i < cols; i++)  {
                    String columnName = JdbcHelper.getColumn(rsmd, i + 1);
                    for (PropertyDescriptor prop: props) {
                        if (columnName.equalsIgnoreCase(prop.getName())) {
                            Class<?> propType = prop.getPropertyType();
                            String propTypeName = propType.getName();
                            Object value = rs.getObject(i + 1);
                            Logger.info("propTypeName:{}, value:{}", propTypeName, value);

                            // 获取setter方法.
                            Method propSetter = prop.getWriteMethod();
                            if (propSetter == null || propSetter.getParameterTypes().length != 1) {
                                break;
                            }

                            // 调用setter方法.
                            Class<?> param = propSetter.getParameterTypes()[0];
                            propSetter.invoke(beanObj, param);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ResultsTransformException("将'ResultSet'结果集转换为'Java Bean'出错!", e);
        }

        return beanObj;
    }

}