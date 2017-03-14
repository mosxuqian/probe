package com.blinkfox.test.javafx.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * JavaFx的Label示例
 * Created by blinkfox on 2017-03-14.
 */
public class LabelSample extends Application {

    /**
     * start方法
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        Image image = new Image(getClass().getResourceAsStream("/javafx/image/labels.jpg"));

        // 带图标的标签
        Label searchLabel = new Label("搜索");
        searchLabel.setGraphic(new ImageView(image));
        searchLabel.setFont(new Font("Arial", 30));
        searchLabel.setTextFill(Color.web("#0076a3"));
        searchLabel.setTextAlignment(TextAlignment.JUSTIFY);

        // 旋转的标签
        Label rotateLabel = new Label("值");
        rotateLabel.setFont(new Font("Cambria", 32));
        rotateLabel.setRotate(220);
        rotateLabel.setTranslateY(50);

        // 折叠标签
        Label wrapLabel = new Label("需要折叠包装的一个很长的标签");
        wrapLabel.setWrapText(true);
        wrapLabel.setTranslateY(50);
        wrapLabel.setPrefWidth(100);

        /* 鼠标进入 */
        wrapLabel.setOnMouseEntered(event -> {
            wrapLabel.setScaleX(1.5);
            wrapLabel.setScaleY(1.5);
        });

        /* 鼠标移除 */
        wrapLabel.setOnMouseExited(event -> {
            wrapLabel.setScaleX(1);
            wrapLabel.setScaleY(1);
        });

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().add(searchLabel);
        hbox.getChildren().add(rotateLabel);
        hbox.getChildren().add(wrapLabel);

        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().add(hbox);
        primaryStage.setTitle("JavaFx的Label示例");
        primaryStage.setWidth(420);
        primaryStage.setHeight(200);
        primaryStage.setScene(scene);
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