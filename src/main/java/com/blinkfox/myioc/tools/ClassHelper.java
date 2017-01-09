package com.blinkfox.myioc.tools;

/**
 * Class操作的工具类
 * Created by blinkfox on 2017-01-09.
 */
public final class ClassHelper {

    /**
     * 根据全路径类名得到应用中对应的Class
     * @param clsName
     * @return
     */
    public static Class getClass(String clsName) {
        try {
            return Class.forName(clsName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到名为" + clsName + "的类!");
        }
    }

}