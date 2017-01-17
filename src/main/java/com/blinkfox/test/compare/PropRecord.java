package com.blinkfox.test.compare;

/**
 * 属性记录bean
 * Created by blinkfox on 2017-01-17.
 */
public class PropRecord {

    // 属性名称
    private String field;

    // 业务名称
    private String name;

    // 修改之前的值
    private String beforeValue;

    // 修改之后的值
    private String afterValue;

    /**
     * 空构造方法
     */
    public PropRecord() {
        super();
    }

    /**
     * 全构造方法
     * @param field
     * @param name
     * @param beforeValue
     * @param afterValue
     */
    public PropRecord(String field, String name, String beforeValue,
                      String afterValue) {
        super();
        this.field = field;
        this.name = name;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
    }

    /* getter 和 setter 方法 */
    public String getField() {
        return field;
    }

    public PropRecord setField(String field) {
        this.field = field;
        return this;
    }

    public String getName() {
        return name;
    }

    public PropRecord setName(String name) {
        this.name = name;
        return this;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public PropRecord setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
        return this;
    }

    public String getAfterValue() {
        return afterValue;
    }

    public PropRecord setAfterValue(String afterValue) {
        this.afterValue = afterValue;
        return this;
    }

}