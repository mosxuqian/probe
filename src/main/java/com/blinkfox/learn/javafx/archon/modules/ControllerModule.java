package com.blinkfox.learn.javafx.archon.modules;

import com.blinkfox.learn.javafx.archon.commons.ControllerFactoryImpl;
import com.blinkfox.learn.javafx.archon.commons.IControllerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Controller 的Guice Module
 * Created by blinkfox on 2017-03-22.
 */
public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IControllerFactory.class).to(ControllerFactoryImpl.class).in(Singleton.class);
    }

}