package com.blinkfox.test.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * B类.
 * @author blinkfox on 2017-01-04.
 */
public class B {

    private static final Logger log = LoggerFactory.getLogger(B.class);

    /**
     * do someting2...
     */
    @CostTime
    public void doSomeThing3() {
        log.info("执行B类中doSomeThing3()方法！");
    }

}