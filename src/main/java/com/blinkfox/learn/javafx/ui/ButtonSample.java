package com.blinkfox.learn.javafx.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * JavaFx的Button示例
 * Created by blinkfox on 2017/3/14.
 */
public class ButtonSample extends Application {

    /**
     * start方法.
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        Color color = Color.web("#464646");
        Scene scene = new Scene(new Group());
        scene.getStylesheets().add("/javafx/css/ButtonStyle.css");

        Label label = new Label();
        label.setFont(Font.font("Times New Roman", 22));
        label.setTextFill(color);

        Image okImage = new Image(getClass().getResourceAsStream("/javafx/image/ok.png"));


        Button btn1 = new Button("同意", new ImageView(okImage));
        btn1.getStyleClass().add("button1");
        btn1.setOnAction(event -> {
            Logger.info("点击了按钮1");
            label.setText("按钮1同意啦！");
        });

        Button btn2 = new Button("同意");
        btn2.setOnAction(event -> {
            Logger.info("点击了按钮2");
            label.setText("按钮2同意啦！");
        });

        DropShadow shadow = new DropShadow();
        Button btn3 = new Button("拒绝");
        btn3.setOnAction(event -> {
            Logger.info("点击了按钮3");
            label.setText("按钮3拒绝了！");
        });
        btn3.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> btn3.setEffect(shadow));
        btn3.addEventHandler(MouseEvent.MOUSE_EXITED, event -> btn3.setEffect(null));

        Button btn4 = new Button();
        btn4.setGraphic(new ImageView(okImage));
        btn4.setOnAction(event -> {
            Logger.info("点击了按钮4");
            label.setText("按钮4同意了！");
        });

        Button btn5 = new Button();
        Image notImage = new Image(getClass().getResourceAsStream("/javafx/image/not.png"));
        btn5.setGraphic(new ImageView(notImage));
        btn5.setOnAction(event -> {
            Logger.info("点击了按钮5");
            label.setText("按钮5拒绝了！");
        });

        VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(20);
        HBox hbox1 = new HBox();

        hbox1.getChildren().add(btn2);
        hbox1.getChildren().add(btn3);
        hbox1.getChildren().add(label);
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.BOTTOM_CENTER);

        HBox hbox2 = new HBox();
        hbox2.getChildren().add(btn4);
        hbox2.getChildren().add(btn5);
        hbox2.setSpacing(25);

        vbox.getChildren().add(btn1);
        vbox.getChildren().add(hbox1);
        vbox.getChildren().add(hbox2);
        vbox.setSpacing(10);
        ((Group)scene.getRoot()).getChildren().add(vbox);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/javafx/image/ok.png"));
        primaryStage.setTitle("JavaFx的Button示例");
        primaryStage.setWidth(310);
        primaryStage.setHeight(200);
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