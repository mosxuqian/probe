package com.blinkfox.test.other.xml;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JAXB2测试类.
 *
 * @author blinkfox on 2017-12-04.
 */
public class JAXB2Test {

    private static final Logger log = LoggerFactory.getLogger(JAXB2Test.class);

    public static void main(String[] args) {
        Address address = new Address("中国", "北京", "北京", "上地", "100080");
        Person p = new Person(Calendar.getInstance(),"JAXB2", address, Gender.MALE, "软件工程师");

        FileReader reader = null;
        FileWriter writer = null;
        try {
            // 生成xml文件.
            JAXBContext context = JAXBContext.newInstance(Person.class);
            writer = new FileWriter("G:/test/person.xml");
            Marshaller m = context.createMarshaller();
            m.marshal(p, writer);
            log.info("生成person.xml文件成功!");

            // 读取xml文件.
            reader = new FileReader("G:/test/person.xml");
            Unmarshaller um = context.createUnmarshaller();
            Person p2 = (Person) um.unmarshal(reader);
            log.info("Country:{}", p2.getAddress().getCountry());
        } catch (Exception e) {
            log.error("生成和读取XML文件出错！", e);
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(reader);
        }
    }

}