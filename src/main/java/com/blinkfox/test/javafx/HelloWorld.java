package com.blinkfox.test.javafx;

import com.blinkfox.utils.Log;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFx的第一个HelloWorld示例
 * Created by blinkfox on 2017/3/11.
 */
public class HelloWorld extends Application {

    private static final Log log = Log.get(HelloWorld.class);

    /**
     * 开始的方法
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("你好，世界！");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                log.info("说：“你好，世界！”");
            }
        });

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(btn);
        Scene scene = new Scene(stackPane, 300, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}