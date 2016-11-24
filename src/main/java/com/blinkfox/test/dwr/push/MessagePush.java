package com.blinkfox.test.dwr.push;

import com.blinkfox.test.dwr.push.DwrScriptSessionManagerUtil;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import javax.servlet.ServletException;

/**
 * 被推送页面的消息推送类
 * Created by blinkfox on 2016/11/25.
 */
public class MessagePush {

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