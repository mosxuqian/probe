package com.blinkfox.learn.javafx.archon;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * Archon的主启动程序
 * Created by blinkfox on 2017-03-20.
 */
public class ArchonApplication extends Application {

    // 主stage
    private static Stage primaryStage;

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
        primaryStage.setScene(getStartScene());
        setPrimaryStage(primaryStage);
        primaryStage.show();
    }

    /**
     * 获取start界面的scene.
     * @return scene
     */
    private Scene getStartScene() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/fxml/archon/start.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            Logger.info(e, "加载start scene出错！");
        }
        return scene;
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}