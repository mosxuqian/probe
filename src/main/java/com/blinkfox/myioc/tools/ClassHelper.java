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

    /**
     * 根据class得到该类的实例
     * @param cls class实例
     * @return 对象类的实例
     */
    public static Object newInstance(Class cls) {
        try {
            return cls.newInstance();
        }catch (Exception e) {
            throw new RuntimeException("实例化类" + cls.getName() + "出错!");
        }
    }

}