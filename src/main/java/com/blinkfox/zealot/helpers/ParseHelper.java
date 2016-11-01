package com.blinkfox.zealot.helpers;

import com.blinkfox.zealot.bean.BuildSource;
import ognl.Ognl;
import ognl.OgnlException;
import org.mvel2.MVEL;

/**
 * OGNL表达式相关的工具类
 * Created by blinkfox on 2016/10/30.
 */
public class ParseHelper {

    /**
     * 通过ognl来解析表达式的值
     * @param tree
     * @param source
     * @return
     */
    public static Object parseWithOgnl(String tree, BuildSource source) {
        Object obj = false;
        try {
            obj = Ognl.getValue(tree, source.getContext(), source.getParamObj());
        } catch (OgnlException e) {
            System.out.println("-------OGNL表达式执行出错---OgnlException:" + tree);
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 通过MVEL来解析表达式的值
     * @param exp
     * @param source
     * @return
     */
    public static Object parseWithMvel(String exp, BuildSource source) {
        Object obj = false;
        try {
            obj = MVEL.eval(exp, source.getParamObj());
            // obj = Ognl.getValue(exp, source.getContext(), source.getParamObj());
        } catch (Exception e) {
            System.out.println("-------MVEL表达式执行出错:" + exp);
            e.printStackTrace();
        }
        return obj;
    }

}