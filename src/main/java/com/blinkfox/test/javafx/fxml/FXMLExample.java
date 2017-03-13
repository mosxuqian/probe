package com.blinkfox.test.javafx.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

/**
 * JavaFx的FXML使用示例
 * Created by blinkfox on 2017-03-13.
 */
public class FXMLExample extends Application {

    /**
     * start方法
     * @param stage stage
     * @throws IOException 抛出异常
     */
    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("/javafx/fxml/fxml_example.fxml");
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("测试fxml的示例");
        stage.show();
    }

    /**
     * main方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}