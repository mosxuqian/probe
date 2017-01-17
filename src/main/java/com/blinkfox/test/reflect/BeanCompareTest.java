package com.blinkfox.test.reflect;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean属性比较测试类
 * Created by blinkfox on 2017-01-17.
 */
public class BeanCompareTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Date date1 = new Date();
        Date date2 = new Date();
        Person person1 = new Person("111", "张三", 25, date1);
        Person person2 = new Person("111", "李四", 26, date2);
        long startTime = System.currentTimeMillis();
        Map<String, String> resultMap = compare(person1, person2);
        System.out.println("比较耗时:" + (System.currentTimeMillis() - startTime));
        System.out.println(resultMap);
    }

    /**
     * 比较的方法
     * @param obj1
     * @param Obj2
     * @return
     * @throws Exception
     */
    public static Map<String, String> compare(Object obj1, Object Obj2) throws Exception {
        Map<String, String> result = new HashMap<String, String>();
        Field[] fs = obj1.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            Object v1 = f.get(obj1);
            Object v2 = f.get(Obj2);
            result.put(f.getName(), String.valueOf(equals(v1, v2)));
        }
        return result;
    }

    /**
     * 比较相等两对象值是否相等的判断方法
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    /**
     * 测试bean
     * @author blinkfox
     * @date 2016-01-17
     */
    public static class Person {

        private String id;

        private String name;

        private int age;

        private Date birthday;

        /**
         * 空构造方法
         */
        public Person() {
            super();
        }

        /**
         * 全构造方法
         * @param id
         * @param name
         * @param age
         * @param birthday
         */
        public Person(String id, String name, int age, Date birthday) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.birthday = birthday;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

    }

}