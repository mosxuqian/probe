package com.blinkfox.learn.javafx.address.model;

import com.blinkfox.learn.javafx.address.utils.LocalDateAdapter;
import javafx.beans.property.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * 联系人信息类
 * Created by blinkfox on 2017/3/15.
 */
public class Person {

    // 姓
    private final StringProperty familyName;

    // 名
    private final StringProperty lastName;

    // 生日
    private final ObjectProperty<LocalDate> birthday;

    // 城市
    private final StringProperty city;

    // 街道
    private final StringProperty street;

    // 邮编
    private final IntegerProperty postalCode;

    /**
     * 空构造方法
     */
    public Person() {
        this(null, null, null);
    }

    /**
     * 普通构造方法
     * @param familyName 姓
     * @param lastName 名
     */
    public Person(String familyName, String lastName, LocalDate date) {
        this.familyName = new SimpleStringProperty(familyName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = new SimpleObjectProperty<>(date);

        // 为方便测试的初始化数据
        this.city = new SimpleStringProperty("成都");
        this.street = new SimpleStringProperty("天府二街");
        this.postalCode = new SimpleIntegerProperty(123456);
    }

    /* JavaFx的getter和setter方法 */
    public String getFamilyName() {
        return familyName.get();
    }

    public StringProperty familyNameProperty() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName.set(familyName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * 获取生日，并添加LocalDate适配器
     * @return
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getPostalCode() {
        return postalCode.get();
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

}