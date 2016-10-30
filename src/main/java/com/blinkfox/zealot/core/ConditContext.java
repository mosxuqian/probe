package com.blinkfox.zealot.core;

import com.blinkfox.zealot.bean.BuildSource;
import com.blinkfox.zealot.bean.SqlInfo;
import com.blinkfox.zealot.consts.ZealotConst;

/**
 * 构建动态条件查询的上下文协调类
 * Created by blinkfox on 2016/10/30.
 */
public class ConditContext {

    /* 查询条件的前缀 */
    private static final String AND_PREFIX = " AND ";
    private static final String OR_PREFIX = " OR ";

    private static IConditHandler newEqualHandler() {
        return new EqualHandler();
    }

    private static IConditHandler newLikeHandler() {
        return new LikeHandler();
    }

    /**
     * 根据类型和对应的构建参数构造出对应标签的sql和参数
     * @param source
     * @param type
     * @return
     */
    public static SqlInfo buildSqlInfo(BuildSource source, String type) {
        if (ZealotConst.EQUAL.equals(type)) {
            return newEqualHandler().buildSqlInfo(source);
        } else if (ZealotConst.AND_EQUAL.equals(type)) {
            source.setPrefix(AND_PREFIX);
            return newEqualHandler().buildSqlInfo(source);
        } else if (ZealotConst.OR_EQUAL.equals(type)) {
            source.setPrefix(OR_PREFIX);
            return newEqualHandler().buildSqlInfo(source);
        } else if (ZealotConst.LIKE.equals(type)) {
            return newLikeHandler().buildSqlInfo(source);
        } else if (ZealotConst.AND_LIKE.equals(type)) {
            source.setPrefix(AND_PREFIX);
            return newLikeHandler().buildSqlInfo(source);
        } else if (ZealotConst.OR_LIKE.equals(type)) {
            source.setPrefix(OR_PREFIX);
            return newLikeHandler().buildSqlInfo(source);
        } else {
            return source.getSqlInfo();
        }
    }

}