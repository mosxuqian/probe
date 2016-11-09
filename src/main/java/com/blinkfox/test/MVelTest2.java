package com.blinkfox.test;

import org.mvel2.MVEL;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blinkfox on 2016/11/9.
 */
public class MVelTest2 {

    public static void main(String[] args) {
        String expression ="foobar > 99";
        Serializable compiled = MVEL.compileExpression(expression);
        Map vars = new HashMap();
        vars.put("foobar",new Integer(100));
        Boolean result = (Boolean) MVEL.executeExpression(compiled, vars);
        if (result.booleanValue()) {
            System.out.println("执行生效了!");
        }
    }

}