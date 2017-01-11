package com.blinkfox.myioc.exceptioni;

/**
 * 自定义的根据“类名生成类的Class”异常
 * Created by blinkfox on 2017-01-11.
 */
public class ClassForNameExecption extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 默认无参构造方法
     */
    public ClassForNameExecption() {
        super();
    }

    /**
     * 附带日志消息参数的构造方法
     * @param msg 日志消息
     */
    public ClassForNameExecption(String msg) {
        super(msg);
    }

    /**
     * 附带日志消息和原异常信息参数的构造方法
     * @param msg 日志信息
     * @param t 异常信息
     */
    public ClassForNameExecption(String msg, Throwable t) {
        super(msg, t);
    }

}
