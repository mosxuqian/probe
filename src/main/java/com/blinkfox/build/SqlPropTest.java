package com.blinkfox.build;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by blinkfox on 16-6-1.
 */
public class SqlPropTest {

    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        //获取properties文件的输入流，准备进行读操作
        InputStream in = SqlPropTest.class.getResourceAsStream("/sql/sql.properties");
        prop.load(in);  //把输入流加载进来

        // 根据key读取value
        String userSql = prop.getProperty("queryUsersByAge");   //属性读取
        System.out.println("读取到的sql:" + userSql);

        // 遍历所有属性
        Enumeration en = prop.propertyNames(); //得到配置文件的名字
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = prop.getProperty(strKey);
            System.out.println("prop key-value:" +strKey + "=" + strValue);
        }

        // 写入Properties信息
        OutputStream out = new FileOutputStream("sql.properties");
        prop.setProperty("queryAllUsers", "select * from user");
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        prop.store(out, "Update queryAllUsers name property");


        // JVM中的系统properties
        //Properties pps = System.getProperties();
        //pps.list(System.out);
    }

}