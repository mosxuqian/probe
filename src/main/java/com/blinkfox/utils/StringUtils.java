package com.blinkfox.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * Created by blinkfox on 2017-02-22.
 */
public final class StringUtils {

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 私有构造方法
     */
    private StringUtils() {
        super();
    }

    /**
     * 判断是否为空字符串，包括空格也算
     * @param str 待判断字符串
     * @return 是否的布尔结果
     */
    public static boolean isBlank(String str) {
        int strLen;
        if(str == null || (strLen = str.length()) == 0) {
            return true;
        }

        // 遍历每个空格是否有非空格元素
        for(int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     * @param str 待判断字符串
     * @return 是否的布尔结果
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 快速连接参数中的字符串
     * @param strs 各字符串参数
     * @return 连接后的字符串
     */
    public static String concat(String ... strs) {
        StringBuilder sb = new StringBuilder(StringUtils.EMPTY);
        for (String str: strs) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 将null型字符串转成空字符串
     * @param s 待转换字符串
     * @return 转后的字符串
     */
    public static String null2Str(String s) {
        return s == null ? StringUtils.EMPTY : s;
    }

    /**
     * 使用正则表达式校验字符串
     * @param pattern 正则表达式
     * @param strValue 字符串内容
     * @return
     */
    public static boolean matchPattern(String pattern, String strValue){
        if (pattern == null || strValue == null) {
            return false;
        }
        Pattern pt = Pattern.compile(pattern);
        Matcher matcher = pt.matcher(strValue);
        return matcher.find();
    }
}