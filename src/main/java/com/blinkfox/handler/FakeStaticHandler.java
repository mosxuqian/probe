package com.blinkfox.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤html、pdf之类的静态文件，防止直接访问
 * Created by blinkfox on 16/7/27.
 */
public class FakeStaticHandler extends Handler {

    @Override
    public void handle(String target, HttpServletRequest request,
             HttpServletResponse response, boolean[] isHandled) {
        String[] typeArr = {".html", ".txt"};
        for (int i = 0; i<typeArr.length; i++){
            int index = target.lastIndexOf(typeArr[i]);
            if (index != -1) {
                target = target.substring(0, index);
                break;
            }
        }
        nextHandler.handle(target, request, response, isHandled);
    }

}