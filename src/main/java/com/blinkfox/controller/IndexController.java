package com.blinkfox.controller;

import com.jfinal.core.Controller;

/**
 * JFinal测试类
 * Created by blinkfox on 16/7/24.
 */
public class IndexController extends Controller {

    public void index() {
        renderText("Hello JFinal World!");
    }

    public void testDwr() {
        render("/app/dwr/demo1.html");
    }

    public void testpush() {
        render("/app/dwr/testpush.html");
    }

    public void showmsg() {
        render("/app/dwr/showmsg.html");
    }

}