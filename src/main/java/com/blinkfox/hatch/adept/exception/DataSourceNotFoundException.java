package com.blinkfox.hatch.adept.exception;

/**
 * 未找到数据源DataSource异常.
 * Created by blinkfox on 2017/6/5.
 */
public class DataSourceNotFoundException extends RuntimeException {

    /**
     * 附带msg信息的构造方法.
     * @param msg 消息
     */
    public DataSourceNotFoundException(String msg) {
        super(msg);
    }

}