package com.blinkfox.test.compare;

import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blinkfox on 2017/1/18.
 */
public class CompareTest {

    private static final Map<String, String> personPropMap = new HashMap<String, String>(){{
        put("id", "用户ID");
        put("name", "名称");
        put("age", "年龄");
        put("birthday", "生日");
    }};

    public static void main(String[] args) {
        Date date = new Date();
        Person person1 = new Person("111", "张三", 25, date);
        Person person2 = new Person("222", "李思", 25, date);
        long start = System.currentTimeMillis();
        List<PropRecord> props = ObjCompareUtils.compare(person1, person2, personPropMap);
        StringBuilder sb = new StringBuilder("获取不同属性集合耗时:")
                .append(System.currentTimeMillis() - start).append(" ms");
        System.out.println("耗时:" + sb.toString() + ",结果:" + JSON.toJSON(props));
    }

}