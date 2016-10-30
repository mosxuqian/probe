package com.blinkfox.zealot.helpers;

import com.blinkfox.zealot.bean.BuildSource;
import ognl.Ognl;
import ognl.OgnlException;

/**
 * OGNL表达式相关的工具类
 * Created by blinkfox on 2016/10/30.
 */
public class OgnlHelper {

    public static Object parseWithOgnl(String tree, BuildSource source) {
        Object obj = false;
        try {
            obj = Ognl.getValue(tree, source.getContext(), source.getParamMap());
        } catch (OgnlException e) {
            System.out.println("-------OGNL表达式执行出错---OgnlException:" + tree);
            e.printStackTrace();
        }
        return obj;
    }

}