package com.blinkfox.hatch.adept.test;

import com.blinkfox.bean.UserInfo;
import com.blinkfox.hatch.adept.core.IntrospectorManager;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * IntrospectorManager类的单元测试类.
 * Created by blinkfox on 2017/6/17.
 */
public class IntrospectorManagerTest {

    private static IntrospectorManager introManager;

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
    }

    @AfterClass
    public static void destroy() {
        introManager = null;
    }

}