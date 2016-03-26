package com.blinkfox.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器的注解使用示例
 * Created by blinkfox on 16-3-26.
 */
//@WebFilter(filterName = "HelloFilter", urlPatterns = "/*",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class HelloFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化Filter......");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        System.out.println("过滤到的此uri地址是：" + httpReq.getRequestURI());
        chain.doFilter(req, resp);
    }

    public void destroy() {
        System.out.println("结束Filter......");
    }

}
