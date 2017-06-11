package com.blinkfox.hatch.adept.helpers;

import com.blinkfox.hatch.adept.exception.AdeptRuntimeException;

/**
 * class、反射等的工具类.
 * Created by blinkfox on 2017/6/12.
 */
public final class ClassHelper {

    /**
     * 私有构造方法.
     */
    private ClassHelper() {
        super();
    }

    /**
     * 根据class生成该class对应类的新实例.
     * @param clazz class
     * @return 泛型T
     */
    public static Object newInstanceByClass(Class<?> clazz) {
        try {
            return Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            throw new AdeptRuntimeException("实例化class出错！", e);
        }
    }

}