package com.blinkfox.zealot.core.concrete;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.core.IConditHandler;

/**
 * in查询动态sql生成的实现类
 * Created by blinkfox on 2016/10/31.
 */
public class InHandler implements IConditHandler {

    /**
     * 构建in查询的动态条件sql
     * @param source
     * @return
     */
    @Override
    public SqlInfo buildSqlInfo(BuildSource source) {
        return null;
    }

}