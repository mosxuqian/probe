package com.blinkfox.learn.javafx.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * RectangleDemo.
 * Created by blinkfox on 2017-03-23.
 */
public class RectangleDemo extends Application {

    /**
     * 开始方法.
     * @param primaryStage 主stage
     * @throws Exception 异常
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // 构造 Rectangle
        int rows = 20;
        int columns = 10;
        int horizontal = 20;
        int vertical = 20;
        Rectangle rect;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                rect = new Rectangle(horizontal * i, vertical * j, horizontal, vertical);
                rect.setStroke(Color.RED);
                root.getChildren().add(rect);
            }
        }

        scene.setRoot(root);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}