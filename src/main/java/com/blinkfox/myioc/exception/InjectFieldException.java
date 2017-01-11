package com.blinkfox.myioc.exception;

/**
 * 自定义的“注入依赖字段”异常
 * Created by blinkfox on 2017/1/10.
 */
public class InjectFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 默认无参构造方法
     */
    public InjectFieldException() {
        super();
    }

    /**
     * 附带日志消息参数的构造方法
     * @param msg 日志消息
     */
    public InjectFieldException(String msg) {
        super(msg);
    }

    /**
     * 附带日志消息和原异常信息参数的构造方法
     * @param msg 日志信息
     * @param t 异常信息
     */
    public InjectFieldException(String msg, Throwable t) {
        super(msg, t);
    }

}