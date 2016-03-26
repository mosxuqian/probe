package com.blinkfox.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by blinkfox on 16-3-26.
 */
@WebFilter(filterName = "asyncFilter1", urlPatterns = "/filter1", asyncSupported = true,
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ASYNC})
public class AsyncFilter1 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("-------------filter1 before--------");
        chain.doFilter(req, resp);
        System.out.println("-------------filter1 after--------");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
