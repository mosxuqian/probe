package com.blinkfox.zealot.consts;

/**
 * 常量接口
 * Created by blinkfox on 2016/10/30.
 */
public class ZealotConst {

    /* 节点类型 */
    public static final String NODETYPE_TEXT = "Text"; // 文本节点
    public static final String NODETYPE_ELEMENT = "Element"; // 元素节点

    /* 元素节点类型 ElementType->ET */
    public static final String EQUAL = "equal";
    public static final String AND_EQUAL = "andEqual";
    public static final String OR_EQUAL = "orEqual";
    public static final String LIKE = "like";
    public static final String AND_LIKE = "andLike";
    public static final String OR_LIKE = "orLike";
    public static final String NUM_BETWEEN = "numBetween";
    public static final String AND_NUM_BETWEEN = "andNumBetween";
    public static final String OR_NUM_BETWEEN = "orNumBetween";

    /* 属性节点类型 */
    public static final String ATTR_MATCH = "attribute::match";
    public static final String ATTR_FIELD = "attribute::field";
    public static final String ATTR_VALUE = "attribute::value";

}