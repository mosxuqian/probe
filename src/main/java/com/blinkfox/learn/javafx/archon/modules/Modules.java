package com.blinkfox.learn.javafx.archon.modules;

import com.google.inject.AbstractModule;

/**
 * 管理依赖注入所有Module的主要Module.
 * Created by blinkfox on 2017-03-22.
 */
public class Modules extends AbstractModule {

    @Override
    protected void configure() {
        install(new ControllerModule());
        install(new ServiceModule());
    }

}