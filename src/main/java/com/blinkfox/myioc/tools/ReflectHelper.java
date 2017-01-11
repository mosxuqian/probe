package com.blinkfox.myioc.tools;

import com.blinkfox.myioc.exceptioni.InjectFieldException;
import java.lang.reflect.Field;

/**
 * Class操作的工具类
 * Created by blinkfox on 2017-01-09.
 */
public final class ReflectHelper {

    /**
     * 根据全路径类名得到应用中对应的Class
     * @param clsName class名称
     * @return 类的class
     */
    public static Class getClass(String clsName) {
        try {
            return Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到名为" + clsName + "的类!");
        }
    }

    /**
     * 动态注入依赖字段的引用
     * @param cls 待注入的提供者Class
     * @param obj 待注入的提供者实例
     * @param injectObj 需要依赖注入的实例
     * @param fieldName 需要依赖注入的字段名称
     * @return 反射字段
     */
    public static Field setFieldValue(Class cls, Object obj, Object injectObj,String fieldName) {
        try {
            Field field = cls.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, injectObj);
            return field;
        } catch (Exception e) {
            throw new InjectFieldException("动态注入依赖字段的引用出错！");
        }
    }

}