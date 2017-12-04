package com.blinkfox.test.other.xml;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Person类.
 *
 * @author blinkfox on 2017-12-04.
 */
@XmlRootElement
public class Person {

    /** birthday将作为person的子元素. */
    @XmlElement
    Calendar birthDay;

    /** name将作为person的的一个属性. */
    @XmlAttribute
    String name;

    /** address将作为person的子元素. */
    @XmlElement
    Address address;

    /** gender将作为person的子元素. */
    @XmlElement
    Gender gender;

    /** job将作为person的子元素. */
    @XmlElement
    String job;

    /**
     * 默认的空构造方法.
     */
    public Person() {
        super();
    }

    public Person(Calendar birthDay, String name, Address address, Gender gender, String job) {
        this.birthDay = birthDay;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.job = job;
    }

    /**
     * address的getter方法.
     * @return address
     */
    public Address getAddress() {
        return address;
    }

}