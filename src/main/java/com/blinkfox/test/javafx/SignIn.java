package com.blinkfox.test.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFx的SignIn示例
 * Created by blinkfox on 2017/3/12.
 */
public class SignIn extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // 欢迎标题
        Text sceneTitle = new Text("欢迎登录");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        // 用户账户
        Label userLabel = new Label("账户：");
        grid.add(userLabel, 0, 1);
        TextField userField = new TextField();
        grid.add(userField, 1, 1);

        // 用户密码
        Label pwdLabel = new Label("账户：");
        grid.add(pwdLabel, 0, 2);
        TextField pwdField = new TextField();
        grid.add(pwdField, 1, 2);

        // 登录按钮
        Button btn = new Button("登录");
        HBox hbtn = new HBox(10);
        hbtn.setAlignment(Pos.BOTTOM_CENTER);
        hbtn.getChildren().add(btn);
        grid.add(hbtn, 1, 4);

        // 显示提示信息的文本
        final Text actionTarget = new Text();
        grid.add(actionTarget, 0, 6);
        grid.setColumnSpan(actionTarget, 2);
        grid.setHalignment(actionTarget, HPos.RIGHT);
        actionTarget.setId("actionTarget");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("点击了登录按钮");
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFx SignIn Demo");
        primaryStage.show();
    }

}