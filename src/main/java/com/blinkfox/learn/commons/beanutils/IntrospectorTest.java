package com.blinkfox.learn.commons.beanutils;

import com.blinkfox.bean.PropBean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.pmw.tinylog.Logger;

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
        printPropInfo(PropBean.class);
    }

    /**
     * 打印JavaBean的属性信息.
     * @param clazz JavaBean的class
     */
    private static void printPropInfo(Class<?> clazz) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop: props) {
            Class<?> propType = prop.getPropertyType();
            Logger.info("prop name:{}, propType:{}", prop.getName(), propType.getName());
        }
    }

}