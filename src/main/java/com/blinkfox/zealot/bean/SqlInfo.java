package com.blinkfox.zealot.bean;

import com.blinkfox.zealot.helpers.StringHelper;

import java.util.List;

/**
 * Created by blinkfox on 2016/10/30.
 */
public class SqlInfo {

    private StringBuffer join;

    // sql语句对应的有序参数
    private List<Object> params;

    /**
     * 构造方法
     * @param join
     */
    public SqlInfo(StringBuffer join) {
        this.join = join;
    }

    /**
     * 构造方法
     * @param join
     * @param params
     */
    public SqlInfo(StringBuffer join, List<Object> params) {
        super();
        this.join = join;
        this.params = params;
    }

    /*getter和setter方法*/
    public String getSql() {
        return join == null ? "" : StringHelper.replaceBlank(join.toString());
    }

    public StringBuffer getJoin() {
        return join;
    }

    public SqlInfo setJoin(StringBuffer join) {
        this.join = join;
        return this;
    }

    public List<Object> getParams() {
        return params;
    }

    public SqlInfo setParams(List<Object> params) {
        this.params = params;
        return this;
    }

    /**
     * 得到参数的对象数组
     * @return
     */
    public Object[] getParamsArr() {
        return params == null ? new Object[]{} : this.params.toArray();
    }

}