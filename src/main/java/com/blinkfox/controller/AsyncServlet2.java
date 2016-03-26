package com.blinkfox.controller;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by blinkfox on 16-3-26.
 */
@WebServlet(name = "asyncServlet2", urlPatterns = "/async2", asyncSupported = true)
public class AsyncServlet2 extends HttpServlet {

    private final ExecutorService executorService = Executors.newScheduledThreadPool(2);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        // 把任务提交给自己的任务队列
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3L * 1000);
                    System.out.println("执行到这里1111。。。。。。。");
                    PrintWriter out = asyncContext.getResponse().getWriter();
                    out.write("执行结束");
                    asyncContext.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 4、当前线程可立即返回
        System.out.println("执行到这里2222。。。。。。。");
    }

}
