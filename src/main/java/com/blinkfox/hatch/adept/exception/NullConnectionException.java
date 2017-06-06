package com.blinkfox.hatch.adept.exception;

/**
 * Null Connection异常.
 * Created by blinkfox on 2017/6/7.
 */
public class NullConnectionException extends RuntimeException {

    /**
     * 附带msg信息的构造方法.
     * @param msg 消息
     */
    public NullConnectionException(String msg) {
        super(msg);
    }

}