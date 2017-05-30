package com.blinkfox.learn.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.beans.BeanGenerator;
import org.pmw.tinylog.Logger;

/**
 * CglibTest.
 * Created by blinkfox on 2017/5/30.
 */
public class CglibTest {

    public static void main(String[] args) throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name", String.class);
        Object myBean = beanGenerator.create();

        Method setter = myBean.getClass().getMethod("setName", String.class);
        setter.invoke(myBean, "Hello 这是我的名字!");
        Method getter = myBean.getClass().getMethod("getName");
        Logger.info("getter的值：{}", getter.invoke(myBean));
    }

}