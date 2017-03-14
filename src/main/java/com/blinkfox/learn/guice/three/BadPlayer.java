package com.blinkfox.learn.guice.three;

import com.blinkfox.utils.Log;

/**
 * 差的运动员
 * Created by blinkfox on 2017-03-14.
 */
public class BadPlayer implements IPlay {

    private static final Log log = Log.get(BadPlayer.class);

    @Override
    public void run() {
        log.info("我跑的很慢！");
    }

    @Override
    public void jump() {
        log.info("我跳的很近！");
    }

}