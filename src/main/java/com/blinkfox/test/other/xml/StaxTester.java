package com.blinkfox.test.other.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Stax测试类.
 *
 * @author blinkfox on 2017-12-04.
 */
public class StaxTester {

    /**
     * 根据StAX读取XML文件.
     *
     * @throws XMLStreamException XML流异常
     * @throws FileNotFoundException 文件未找到异常
     */
    private static void readXxmlByStax() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLEventReader xmler = xmlif.createXMLEventReader(new FileInputStream("G:\\test\\test.xml"));
        XMLEvent event;
        StringBuilder sb = new StringBuilder();
        while (xmler.hasNext()) {
            event = xmler.nextEvent();
            if (event.isStartElement()) { //如果解析的是起始标记
                StartElement element = event.asStartElement();
                sb.append("<");
                sb.append(element.getName());
                if(element.getName().getLocalPart().equals("catalog")) {
                    sb.append(" id=/");
                    sb.append(element.getAttributeByName(new QName("id")).getValue());
                    sb.append("/");
                }
                sb.append(">");
            } else if (event.isCharacters()) { //如果解析的是文本内容
                sb.append(event.asCharacters().getData());
            } else if(event.isEndElement()) { //如果解析的是结束标记
                sb.append("</");
                sb.append(event.asEndElement().getName());
                sb.append(">");
            }
        }
        System.out.println(sb);
    }

    /**
     * 根据StAX写入XML文件.
     *
     * @throws XMLStreamException XML流异常
     * @throws FileNotFoundException 文件未找到异常
     */
    private static void writeXmlByStax() throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("G:\\test\\output.xml"));
        // 写入默认的 XML 声明到xml文档
        xmlw.writeStartDocument();
        xmlw.writeCharacters("\n");
        // 写入注释到xml文档
        xmlw.writeComment("testing comment");
        xmlw.writeCharacters("\n");
        // 写入一个catalogs根元素
        xmlw.writeStartElement("catalogs");
        xmlw.writeNamespace("myNS", "http://blinkfox.com");
        xmlw.writeAttribute("owner","Chinajash");
        xmlw.writeCharacters("\n");
        // 写入子元素catalog
        xmlw.writeCharacters("    ");
        xmlw.writeStartElement("http://blinkfox.com", "catalog");
        xmlw.writeAttribute("id","007");
        xmlw.writeCharacters("Apparel");
        // 写入catalog元素的结束标签
        xmlw.writeEndElement();
        // 写入catalogs元素的结束标签
        xmlw.writeCharacters("\n");
        xmlw.writeEndElement();
        // 结束 XML 文档
        xmlw.writeEndDocument();
        xmlw.close();
        System.out.println("生成xml文件成功!");
    }

    /**
     * main方法.
     *
     * @param args 数组参数
     * @throws XMLStreamException XML流异常
     * @throws FileNotFoundException 文件未找到异常
     */
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        readXxmlByStax();
        writeXmlByStax();
    }

}