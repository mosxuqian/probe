package com.blinkfox.learn.javafx.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 主运行类
 * Created by blinkfox on 2017/3/14.
 */
public class Main extends Application {

    /**
     * start
     * @param primaryStage start
     * @throws Exception e
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/fxml/PersonOverview.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * main方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}