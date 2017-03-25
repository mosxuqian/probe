package com.blinkfox.learn.javafx.ui.tree;

import javafx.beans.property.SimpleStringProperty;

/**
 * 雇员实体bean.
 * Created by blinkfox on 2017/3/26.
 */
public class Employee {

    // 名称
    private final SimpleStringProperty name;

    // 部门
    private final SimpleStringProperty department;

    /**
     * 构造方法.
     * @param name 名称
     * @param department 部门
     */
    public Employee(String name, String department) {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
    }

    /* getter 和 setter 方法 */
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDepartment() {
        return department.get();
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

}