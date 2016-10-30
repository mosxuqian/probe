package com.blinkfox.zealot.bean;

import ognl.OgnlContext;
import org.dom4j.Node;
import java.util.Map;

/**
 * 构建动态sql和参数相关的bean
 * Created by blinkfox on 2016/10/30.
 */
public class BuildSource {

    private SqlInfo sqlInfo; // sql拼接信息
    private Node node; // xml节点
    private OgnlContext context; // OGNL上下文
    private Map<String, Object> paramMap; // 参数

    private String prefix; // 前缀
    private String suffix; // 后缀


    public BuildSource() {
        super();
    }
    public BuildSource(SqlInfo sqlInfo, Node node, OgnlContext context,
                       Map<String, Object> paramMap) {
        super();
        this.sqlInfo = sqlInfo;
        this.node = node;
        this.context = context;
        this.paramMap = paramMap;
    }


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
    public Map<String, Object> getParamMap() {
        return paramMap;
    }
    public BuildSource setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
        return this;
    }
    public String getPrefix() {
        return prefix;
    }
    public BuildSource setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
    public String getSuffix() {
        return suffix;
    }
    public BuildSource setSuffix(String suffix) {
        this.suffix = suffix;
        return this;
    }

}