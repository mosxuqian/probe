package com.blinkfox.myioc.tools;

/**
 * String工具类
 * Created by blinkfox on 2017/1/9.
 */
public final class StringHelper {

    /**
     * 私有构造方法
     */
    private StringHelper() {
        super();
    }

    /**
     * 从全路径的类名中提取出驼峰式命名的名称，即：'HelloWorld'转换为'helloWorld'
     * @param clsName 全路径类名
     * @return 驼峰式字符串
     */
    public static String getCamelByClsName(String clsName) {
        String[] names = clsName.split("\\.");
        String lastName = names[names.length - 1];
        if(Character.isUpperCase(lastName.charAt(0))) {
            StringBuilder sb = new StringBuilder(lastName.length());
            lastName = sb.append(Character.toLowerCase(lastName.charAt(0)))
                    .append(lastName.substring(1)).toString();
        }
        return lastName;
    }

}