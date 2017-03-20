package com.blinkfox.learn.javafx.mutlistage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * test mutliStage change scene.
 * Created by blinkfox on 2017-03-20.
 */
public class Test extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene scene = logInScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * create loginScene.
     * @return scene
     */
    private Scene logInScene() {
        Pane root = new Pane();
        Button createAccountButton = new Button("创建账户");
        createAccountButton.setOnAction(t -> stage.setScene(createAccountScene()));
        root.getChildren().add(createAccountButton);
        return new Scene(root);
    }

    /**
     * Create AccountScene.
     * @return scene
     */
    private Scene createAccountScene() {
        VBox root = new VBox();
        Label userLabel = new Label("Insert the username:");
        final TextField userField = new TextField();
        Button createAccountButton = new Button("create account");
        createAccountButton.setOnAction(t ->
                Logger.info("Account for user " + userField.getText() + " was created succesfully"));

        Button backLoginBtn = new Button("返回登录");
        backLoginBtn.setOnAction(t -> stage.setScene(logInScene()));

        root.getChildren().addAll(userLabel, userField, createAccountButton, backLoginBtn);
        return new Scene(root);
    }

    public static void main(String[] args) {
        launch(args);
    }

}