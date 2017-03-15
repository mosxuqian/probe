package com.blinkfox.learn.javafx.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

import java.io.IOException;

/**
 * 主运行类
 * Created by blinkfox on 2017/3/14.
 */
public class MainApp extends Application {

    private Stage primaryStage;

    private BorderPane rootLayout;

    /**
     * start
     * @param primaryStage start
     * @throws Exception e
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
        showPersonOverview();
        primaryStage.setTitle("联系人管理应用");
        primaryStage.getIcons().add(new Image("/javafx/image/ok.png"));
        primaryStage.show();
    }

    /**
     * 初始化rootLayout
     */
    private void initRootLayout() {
        try {
            rootLayout = FXMLLoader.load(getClass().getResource("/javafx/fxml/RootLayout.fxml"));
            primaryStage.setScene(new Scene(rootLayout));
        } catch (IOException e) {
            Logger.error(e, "加载RootLayout.fxml文件失败");
        }
    }

    /**
     * 在根布局中显示联系人管理界面
     */
    private void showPersonOverview() {
        try {
            AnchorPane personOverview = FXMLLoader.load(getClass().getResource("/javafx/fxml/PersonOverview.fxml"));
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            Logger.error(e, "加载PersonOverview文件失败");
        }
    }

    /**
     * 得到主要的stage
     * @return stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * main方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}