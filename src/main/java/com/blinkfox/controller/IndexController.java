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

}