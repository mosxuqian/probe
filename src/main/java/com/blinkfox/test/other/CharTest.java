package com.blinkfox.test.other;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import com.blinkfox.test.sorts.others.PhoneNumberSort;
import org.pmw.tinylog.Logger;

/**
 * CharTest.
 * Created by blinkfox on 2017/5/7.
 */
public class CharTest {

    private static final String STR_FORMAT = "00000000000";

    private static final DecimalFormat df = new DecimalFormat(STR_FORMAT);

    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "13880545209";
        int len = msg.getBytes("UTF-8").length;
        Logger.info("len:{}", len);

        Logger.info("手机号1:{}", PhoneNumberSort.toNumStr(13801L));
        Logger.info("手机号2:{}", PhoneNumberSort.toNumStr(13880523L));
        Logger.info("手机号3:{}", PhoneNumberSort.toNumStr(1380003L));
        Logger.info("手机号4:{}", PhoneNumberSort.toNumStr(1380103213L));
        Logger.info("手机号5:{}", PhoneNumberSort.toNumStr(13801032135L));
    }

}