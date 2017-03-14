package com.blinkfox.learn.guice.three;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

/**
 * Player的Guice配置类
 * Created by blinkfox on 2017-03-14.
 */
public class PlayerModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(IPlay.class).annotatedWith(Names.named("goodPlayer")).to(GoodPlayer.class);
        binder.bind(IPlay.class).annotatedWith(Names.named("badPlayer")).to(BadPlayer.class);
    }

}