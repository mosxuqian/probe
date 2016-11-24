package com.blinkfox.test.dwr.push;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import java.util.Collection;

/**
 * 测试发送推送信息的dwr类
 * Created by blinkfox on 2016/11/25.
 */
public class TestPush {

    public void sendMessageAuto(String email, String message){
        final String finalEmail = email;
        final String autoMessage = message;

        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            public boolean match(ScriptSession session){
                if (session.getAttribute("email") == null) {
                    return false;
                } else {
                    return (session.getAttribute("email")).equals(finalEmail);
                }
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();
            public void run(){
                script.appendCall("showMessage", autoMessage);
                Collection<ScriptSession> sessions = Browser.getTargetSessions();

                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript(script);
                }
            }
        });
    }

}