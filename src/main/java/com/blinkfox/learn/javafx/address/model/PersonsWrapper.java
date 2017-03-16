package com.blinkfox.learn.javafx.address.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Person集合的包装工具类，用于将Person集合保存为XML
 * Created by blinkfox on 2017/3/16.
 */
@XmlRootElement(name = "persons")
public class PersonsWrapper {

    private List<Person> persons;

    /**
     * 获取persons
     * @return persons
     */
    @XmlElement(name = "person")
    public List<Person> getPersons() {
        return persons;
    }

    /**
     * 设置persons
     * @param persons persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}