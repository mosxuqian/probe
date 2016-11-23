package com.blinkfox.test.dwr;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 测试DWR过滤器
 * Created by blinkfox on 2016/11/24.
 */
public class TestFilter implements AjaxFilter {

    /**
     * 实现的Filter方法
     * @param o
     * @param method
     * @param objects
     * @param ajaxFilterChain
     * @return
     * @throws Exception
     */
    @Override
    public Object doFilter(Object o, Method method, Object[] objects,
            AjaxFilterChain chain) throws Exception {
        // 获取用户的session信息
        WebContext ctx = WebContextFactory.get();
        HttpSession session = ctx.getSession();
        String email = (String) session.getAttribute("email");
        if (email == null) {
            System.out.println("未获取到用户信息...");
            return "sessionError";
        }

        // 拦截调用方法
        String methodName = method.getName();
        if ("sayHello".equals(methodName)) {
            System.out.println("----拦截目标方法:" + methodName);
            Object obj = chain.doFilter(o, method, objects);
            return obj;
        }

        System.out.println("----目标方法:" + methodName + "执行结束!");
        return null;
    }

}