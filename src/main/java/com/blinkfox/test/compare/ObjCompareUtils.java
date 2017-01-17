package com.blinkfox.test.compare;

import com.blinkfox.utils.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象属性比较工具类
 * Created by blinkfox on 2017-01-17.
 */
public class ObjCompareUtils {

    private static final Log log = Log.get(ObjCompareUtils.class);

    private ObjCompareUtils() {
        super();
    }

    /**
     * 比较的方法
     * @param obj1
     * @param Obj2
     * @return
     * @throws Exception
     */
    public static List<PropRecord> compare(Object obj1, Object Obj2, Map<String, String> propMap) {
        List<PropRecord> props = new ArrayList<PropRecord>();
        Field[] fs = obj1.getClass().getDeclaredFields();
        for (Field f : fs) {
            // 设置属性强制可访问，并通过反射获取到这两对象该属性的值
            f.setAccessible(true);
            Object v1 = null, v2 = null;
            try {
                v1 = f.get(obj1);
                v2 = f.get(Obj2);
            } catch (Exception e) {
                log.error("获取对象的属性值出错!", e);
            }

            // 判断这两对象该属性值是否相等，如果不相等则将差异记录到list集合中
            String fieldName = f.getName();
            if (!equals(v1, v2) && propMap.containsKey(fieldName)) {
                props.add(new PropRecord(f.getName(), propMap.get(fieldName), objToStr(v1), objToStr(v2)));
            }
        }

        return props;
    }

    /**
     * 比较的方法
     * @param obj1
     * @param Obj2
     * @return
     * @throws Exception
     */
    public static List<PropRecord> compare2(Object obj1, Object Obj2, Map<String, String> propMap) {
        List<PropRecord> props = new ArrayList<PropRecord>();
        Class cls = obj1.getClass();
        try {
            for (Map.Entry<String, String> entry: propMap.entrySet()) {
                String key = entry.getKey();
                Field field = cls.getDeclaredField(key);
                field.setAccessible(true);
                Object v1 = field.get(obj1);
                Object v2 = field.get(Obj2);
                if (!equals(v1, v2)) {
                    props.add(new PropRecord(key, entry.getValue(), objToStr(v1), objToStr(v2)));
                }
            }
        } catch (Exception e) {
            log.error("未获取到对象的属性!", e);
        }

        return props;
    }

    /**
     * 比较相等两对象值是否相等的判断方法
     * @param obj1
     * @param obj2
     * @return
     */
    private static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    /**
     * 将对象转成String型
     * @param o
     * @return
     */
    private static String objToStr(Object o) {
        return o == null ? "" : o.toString();
    }

}