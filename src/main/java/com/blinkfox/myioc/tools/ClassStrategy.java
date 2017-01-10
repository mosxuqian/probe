package com.blinkfox.myioc.tools;

import com.blinkfox.myioc.exceptioni.NewInstanceException;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据class生成该class对应策略的实例，使用枚举实现
 * Created by blinkfox on 2017/1/10.
 */
public enum ClassStrategy {

    /**
     * 根据类的class得到该类的唯一实例
     */
    SINGLETON {
        @Override
        public Object getInstance(Class cls) {
            // 先判断map中是否存在此变量，如果存在就直接返回该类的实例，否则生成新的类实例并存入全局的Map中
            String clsName = cls.getName();
            if (clsObjMap.containsKey(clsName)) {
                return clsObjMap.get(clsName);
            }

            try {
                Object obj = cls.newInstance();
                clsObjMap.put(clsName, obj);
                return obj;
            }catch (Exception e) {
                throw new NewInstanceException("实例化类" + clsName + "出错!", e);
            }
        }
    };

    // 存放类class和对应类实例的全局Map，其中key是className,value是类的实例
    private static Map<String, Object> clsObjMap = new HashMap<String, Object>();

    /**
     * 根据类的class获取该类的实例
     * @param cls 类的class
     * @return 类的实例
     */
    public abstract Object getInstance(Class cls);

}