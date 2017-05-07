package com.blinkfox.test.other;

import java.io.UnsupportedEncodingException;
import org.pmw.tinylog.Logger;

/**
 * CharTest.
 * Created by blinkfox on 2017/5/7.
 */
public class CharTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "13880545209";
        int len = msg.getBytes("UTF-8").length;
        Logger.info("len:{}", len);
    }

}