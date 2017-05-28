package com.blinkfox.learn.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Logger;

/**
 * StringUtilsDemo.
 * Created by blinkfox on 2017-05-28.
 */
public class StringUtilsDemo {

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        Logger.info(StringUtils.isEmpty(""));
    }

}