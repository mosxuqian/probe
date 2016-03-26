package com.blinkfox.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注解使用Servlet,初始化参数的示例
 * Created by blinkfox on 16-3-26.
 */
@WebServlet(name = "servlet1", urlPatterns = {"/hello"}, loadOnStartup = 1,
        initParams = {@WebInitParam(name = "msg", value = "Hello, Serlvet!")})
public class Servlet1 extends HttpServlet{

    private String msg;

    public Servlet1() {
        System.out.println("实例化servlet");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        msg = this.getInitParameter("msg");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        System.out.println("msg是：" + msg);
    }

}