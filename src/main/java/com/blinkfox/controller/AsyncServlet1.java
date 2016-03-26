package com.blinkfox.controller;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by blinkfox on 16-3-26.
 */
@WebServlet(name = "asyncServlet1", urlPatterns = "/async1", asyncSupported = true)
public class AsyncServlet1 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Connection", "Keep-Alive");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.write("Hello, Async!");
        out.write("<br>");
        out.flush();

        // 开启异步
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(10L * 1000);
        // 3、告诉容器分派一个新的线程执行异步任务
        // 这种方式的缺点就是可能和请求用同一个线程池，不推荐这种做法；从本质上讲和同步没啥区别（都要占用一个服务器线程）
        // 不过如果请求压力较小，可以使用这种方法（因为有超时设置，可以防止一直不响应）
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3L * 1000);
                    System.out.println("执行到这里1111。。。。。。。");
                    PrintWriter out = asyncContext.getResponse().getWriter();
                    out.write("执行结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 4、当前线程可立即返回
        System.out.println("执行到这里2222。。。。。。。");
    }

}