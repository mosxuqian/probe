package com.blinkfox.learn.javafx.archon.modules;

import com.blinkfox.learn.javafx.archon.servivce.impl.AccountStepServiceImpl;
import com.blinkfox.learn.javafx.archon.servivce.impl.DirStepServiceImpl;
import com.blinkfox.learn.javafx.archon.servivce.impl.UseStepServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * 业务相关的 Guice Module.
 * Created by blinkfox on 2017-03-22.
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AccountStepServiceImpl.class).in(Singleton.class);
        bind(DirStepServiceImpl.class).in(Singleton.class);
        bind(UseStepServiceImpl.class).in(Singleton.class);
    }

}