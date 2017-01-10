package com.blinkfox.myioc.exceptioni;

/**
 * 自定义的“依赖注入提供者未找到”异常
 * Created by blinkfox on 2017-01-10.
 */
public class ProviderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 默认无参构造方法
     */
    public ProviderNotFoundException() {
        super();
    }

    /**
     * 附带日志消息参数的构造方法
     * @param msg 日志消息
     */
    public ProviderNotFoundException(String msg) {
        super(msg);
    }

}