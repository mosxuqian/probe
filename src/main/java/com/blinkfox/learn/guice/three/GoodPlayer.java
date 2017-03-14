package com.blinkfox.learn.guice.three;

import com.blinkfox.utils.Log;

/**
 * 好的运动员
 * Created by blinkfox on 2017-03-14.
 */
public class GoodPlayer implements IPlay {

    private static final Log log = Log.get(GoodPlayer.class);

    @Override
    public void run() {
        log.info("我跑的最快！");
    }

    @Override
    public void jump() {
        log.info("我跳的最远！");
    }

}