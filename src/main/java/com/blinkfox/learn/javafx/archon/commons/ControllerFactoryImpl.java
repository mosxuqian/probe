package com.blinkfox.learn.javafx.archon.commons;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.IOException;
import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.pmw.tinylog.Logger;

/**
 * IContrllerFactory实现类.
 * Created by blinkfox on 2017-03-22.
 */
public class ControllerFactoryImpl implements IControllerFactory {

    private final Injector injector;

    /**
     * 构造方法.
     * @param injector injector
     */
    @Inject
    public ControllerFactoryImpl(final Injector injector) {
        this.injector = Objects.requireNonNull(injector, "injector不能为空");
    }

    /**
     * 加载 FXML 文件，并返回IController实现的实例.
     * @param fxmlFile FXML文件
     * @return IController实现实例
     */
    @Override
    public IController loadController(final String fxmlFile) {
        Objects.requireNonNull(fxmlFile, "fxml文件不能为null");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setControllerFactory(injector::getInstance);

        // 加载 FXML 文件
        Parent view = null;
        try {
            view = loader.load();
        } catch (IOException e) {
            Logger.error(e, "加载 FXML 文件失败!");
        }

        IController controller = loader.getController();
        controller.setView(view);
        return controller;
    }

}