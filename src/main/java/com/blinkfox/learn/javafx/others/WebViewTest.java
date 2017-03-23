package com.blinkfox.learn.javafx.others;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * JavaFx WebView示例.
 * Created by blinkfox on 2017/3/22.
 */
public class WebViewTest extends Application {

    /**
     * start方法.
     * @param primaryStage stage
     * @throws Exception 异常
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        WebView webView = new WebView();
        WebEngine engine = webView.getEngine();
        engine.load("http://blinkfox.com");

        StackPane pane = new StackPane();
        pane.getChildren().add(webView);
        primaryStage.setScene(new Scene(pane, 800, 600));
        primaryStage.show();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}