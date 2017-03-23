package com.blinkfox.learn.javafx.ui;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ListView使用示例.
 * Created by blinkfox on 2017-03-23.
 */
public class ListViewSample extends Application {

    /**
     * start方法.
     * @param primaryStage 主stage
     * @throws Exception 异常
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        final VBox box = new VBox();
        final Label label = new Label("默认文字");
        label.setLayoutX(10);
        label.setLayoutY(115);

        ListView<String> list = new ListView<>();
        list.setPrefWidth(200);
        list.setPrefHeight(150);
        ObservableList<String> items = FXCollections.observableArrayList("张三", "李四", "王五", "马六");
        list.setItems(items);

        box.getChildren().addAll(list, label);
        VBox.setVgrow(list, Priority.ALWAYS);

        list.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov,
                String oldVal, String newVal) -> {
            label.setText(newVal);
            label.setTextFill(Color.RED);
        });

        primaryStage.setScene(new Scene(box, 300, 250));
        primaryStage.setTitle("ListView示例");
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