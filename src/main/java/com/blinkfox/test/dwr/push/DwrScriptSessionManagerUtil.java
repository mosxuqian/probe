package com.blinkfox.test.dwr.push;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.directwebremoting.Container;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;
import org.directwebremoting.servlet.DwrServlet;

/**
 * DWR session管理的工具类
 * Created by blinkfox on 2016/11/25.
 */
public class DwrScriptSessionManagerUtil extends DwrServlet {

    private static final long serialVersionUID = -7504612622407420071L;

    public void init()throws ServletException {
        Container container = ServerContextFactory.get().getContainer();
        ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);

        ScriptSessionListener listener = new ScriptSessionListener() {
            public void sessionCreated(ScriptSessionEvent ev) {
                HttpSession session = WebContextFactory.get().getSession();
                String email = (String) session.getAttribute("email");
                System.out.println("-----一个ScriptSession已经创建了!");
                ev.getSession().setAttribute("email", email);
            }
            public void sessionDestroyed(ScriptSessionEvent ev) {
                System.out.println("xxxxxx一个ScriptSession已经销毁了!");
            }
        };
        manager.addScriptSessionListener(listener);
    }

}