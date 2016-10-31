package com.blinkfox.zealot.bean;

import ognl.OgnlContext;
import org.dom4j.Node;

/**
 * 构建动态sql和参数相关的bean
 * Created by blinkfox on 2016/10/30.
 */
public class BuildSource {

    // sql拼接信息
    private SqlInfo sqlInfo;

    // xml节点
    private Node node;

    // OGNL上下文
    private OgnlContext context;

    // 参数对象
    private Object paramObj;

    // 前缀，默认空字符串
    private String prefix = "";

    /**
     * 空构造方法
     */
    public BuildSource() {

    }

    /**
     * 全构造方法
     * @param sqlInfo
     * @param node
     * @param context
     * @param paramObj
     */
    public BuildSource(SqlInfo sqlInfo, Node node, OgnlContext context, Object paramObj) {
        super();
        this.sqlInfo = sqlInfo;
        this.node = node;
        this.context = context;
        this.paramObj = paramObj;
    }

    /* getter 和 setter 方法 */
    public SqlInfo getSqlInfo() {
        return sqlInfo;
    }
    public BuildSource setSqlInfo(SqlInfo sqlInfo) {
        this.sqlInfo = sqlInfo;
        return this;
    }
    public Node getNode() {
        return node;
    }
    public BuildSource setNode(Node node) {
        this.node = node;
        return this;
    }
    public OgnlContext getContext() {
        return context;
    }
    public BuildSource setContext(OgnlContext context) {
        this.context = context;
        return this;
    }
    public Object getParamObj() {
        return paramObj;
    }
    public BuildSource setParamObj(Object paramObj) {
        this.paramObj = paramObj;
        return this;
    }
    public String getPrefix() {
        return prefix;
    }
    public BuildSource setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

}