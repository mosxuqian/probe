package com.blinkfox.hatch.adept.test;

import com.blinkfox.bean.UserInfo;
import com.blinkfox.hatch.adept.core.IntrospectorManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pmw.tinylog.Logger;

/**
 * IntrospectorManager类的单元测试类.
 * Created by blinkfox on 2017/6/17.
 */
public class IntrospectorManagerTest {

    /** IntrospectorManager实例. */
    private static IntrospectorManager introManager;

    /**
     * 初始化实例.
     */
    @BeforeClass
    public static void init() {
        introManager = IntrospectorManager.newInstance();
    }

    /**
     * 测试获取某个Bean的所有PropertyDescriptor.
     */
    @Test
    public void testGetPropertyDescriptors() throws IntrospectionException {
        PropertyDescriptor[] props = introManager.getPropertyDescriptors(UserInfo.class);
        Assert.assertNotNull(props);
    }

    /**
     * 测试获取某个Bean的所有PropertyDescriptor Map.
     */
    @Test
    public void testGetgetPropMap() throws IntrospectionException {
        Map<String, PropertyDescriptor> propMap = introManager.getPropMap(UserInfo.class);
        Assert.assertNotNull(propMap);
        Assert.assertEquals(7, propMap.size());
    }

    /**
     * 测试构建Bean实例.
     */
    @Test
    public void testBuildObj() throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        Class<?> beanClass = UserInfo.class;
        Map<String, PropertyDescriptor> propMap = introManager.getPropMap(beanClass);

        String columnName = "id";
        Object beanObj = beanClass.newInstance();
        if (propMap.containsKey(columnName)) {
            PropertyDescriptor prop = propMap.get(columnName);
            Class<?> propType = prop.getPropertyType();
            String propTypeName = propType.getName();
            Object value = 3;
            Logger.info("propTypeName:{}, value:{}", propTypeName, value);

            // 获取setter方法.
            Method propSetter = prop.getWriteMethod();
            if (propSetter == null || propSetter.getParameterTypes().length != 1) {
                Logger.warn("类'{}'的属性'{}'没有标准的setter方法", beanClass.getName(), columnName);
            }

            // 调用setter方法.
            // Class<?> param = propSetter.getParameterTypes()[0];
            propSetter.invoke(beanObj, value);
        }
        Assert.assertNotNull(beanObj);
        Logger.info(beanObj);
    }

    /**
     * 最后销毁实例.
     */
    @AfterClass
    public static void destroy() {
        introManager = null;
    }

}