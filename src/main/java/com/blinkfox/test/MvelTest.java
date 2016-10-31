package com.blinkfox.test;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blinkfox on 2016-10-31.
 */
public class MvelTest {

    public static void main(String[] args) {
        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("foobar", 100);
        vars.put("foo", "");

        Boolean result1 = (Boolean) MVEL.eval("foobar > 99", vars);
        System.out.println("----result1:" + result1);

        Boolean result2 = (Boolean) MVEL.eval("foo == empty", vars);
        System.out.println("----result2:" + result2);

        Boolean result3 = (Boolean) MVEL.eval("foo == null", vars);
        System.out.println("----result2:" + result3);
    }

}