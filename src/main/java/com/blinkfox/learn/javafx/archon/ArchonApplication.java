package com.blinkfox.learn.javafx.archon;

import com.blinkfox.learn.javafx.archon.commons.IController;
import com.blinkfox.learn.javafx.archon.commons.IControllerFactory;
import com.blinkfox.learn.javafx.archon.modules.Modules;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Archon的主启动程序
 * Created by blinkfox on 2017-03-20.
 */
public class ArchonApplication extends Application {

    // 主stage
    private static Stage primaryStage;

    // Controller工厂实例
    private final IControllerFactory controllerFactory;

    /**
     * 构造方法，初始化 controllerFactory.
     */
    public ArchonApplication() {
        Injector injector = Guice.createInjector(new Modules());
        this.controllerFactory = injector.getInstance(IControllerFactory.class);
    }

    /**
     * primaryStage的setterfangfa.
     * @param primaryStage 主stage
     */
    private static void setPrimaryStage(Stage primaryStage) {
        ArchonApplication.primaryStage = primaryStage;
    }

    /**
     * 得到主stage.
     * @return primaryStage 主stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * start方法.
     * @param primaryStage 主stage
     * @throws Exception 异常
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(getMainScene());
        setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    /**
     * 获取start界面的scene.
     * @return scene
     */
    private Scene getStartScene() {
        IController controller = controllerFactory.loadController("/javafx/fxml/archon/start.fxml");
        return new Scene(controller.getView());
    }

    /**
     * 获取start界面的scene.
     * @return scene
     */
    private Scene getMainScene() {
        IController controller = controllerFactory.loadController("/javafx/fxml/archon/main.fxml");
        return new Scene(controller.getView());
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}