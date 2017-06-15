package com.blinkfox.learn.commons.beanutils;

import com.blinkfox.bean.UserInfo;
import org.pmw.tinylog.Logger;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * InstospectorTest.
 * Created by blinkfox on 2017/6/15.
 */
public class IntrospectorTest {

    /**
     * main.
     * @param args args
     */
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop: props) {
            Class<?> propType = prop.getPropertyType();
            Logger.info("prop name:{}, propType:{}", prop.getName(), propType.getName());
        }
    }

}