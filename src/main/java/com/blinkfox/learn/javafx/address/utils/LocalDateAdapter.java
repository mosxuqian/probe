package com.blinkfox.learn.javafx.address.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * LocalDate适配器
 * Created by blinkfox on 2017/3/16.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    /**
     * 反编列String为LocalDate类型
     * @param str 字符串
     * @return LocalDate
     * @throws Exception 异常
     */
    @Override
    public LocalDate unmarshal(String str) throws Exception {
        return DateUtils.getStrByDate(str);
    }

    /**
     * 编列LocalDate类型为String型
     * @param date 日期
     * @return 字符串
     * @throws Exception 异常
     */
    @Override
    public String marshal(LocalDate date) throws Exception {
        return DateUtils.getDateStr(date);
    }

}