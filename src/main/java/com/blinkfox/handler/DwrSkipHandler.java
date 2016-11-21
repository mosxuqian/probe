package com.blinkfox.handler;

import com.jfinal.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DWR的路由过滤Handler
 * Created by blinkfox on 2016/11/21.
 */
public class DwrSkipHandler extends Handler {

    /**
     * 如果请求是DWR的请求，则绕过JFinal
     * @param target
     * @param request
     * @param response
     * @param isHandled
     */
    @Override
    public void handle(String target, HttpServletRequest request,
            HttpServletResponse response, boolean[] isHandled) {
        int index = target.indexOf("/dwr/");
        if (index != -1) {
            return;
        }
        next.handle(target, request, response, isHandled);
    }

}