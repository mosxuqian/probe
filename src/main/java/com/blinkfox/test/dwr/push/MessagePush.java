package com.blinkfox.test.dwr.push;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import javax.servlet.ServletException;

/**
 * 被推送页面的消息推送类
 * Created by blinkfox on 2016/11/25.
 */
@RemoteProxy
public class MessagePush {

    @RemoteMethod
    public void onPageLoad(String email) {
        ScriptSession scriptSession = WebContextFactory.get().getScriptSession();
        scriptSession.setAttribute("email", email);
        DwrScriptSessionManagerUtil dwrScriptSessionManagerUtil = new DwrScriptSessionManagerUtil();

        try {
            dwrScriptSessionManagerUtil.init();
            System.out.println("-----已经成功init()初始化了");
        } catch (ServletException e) {
            e.printStackTrace();
            System.out.println("xxxxxxxx初始化dwrScriptSessionManagerUtil.init()失败!");
        }
    }

}