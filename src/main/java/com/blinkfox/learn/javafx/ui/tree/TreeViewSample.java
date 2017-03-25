package com.blinkfox.learn.javafx.ui;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFx TreeView使用示例.
 * Created by blinkfox on 2017/3/25.
 */
public class TreeViewSample extends Application {

    /**
     * start方法.
     * @param primaryStage 主stage
     */
    @Override
    public void start(Stage primaryStage) {
        // 创建根节点
        Node rootIcon = new ImageView(new Image(getClass().getResourceAsStream("/javafx/image/folder_16.png")));
        TreeItem<String> rootItem = new TreeItem<>("文件夹", rootIcon);
        rootItem.setExpanded(true);

        // 循环创建子节点和TreeView
        for (int i = 0; i < 5; i++) {
            TreeItem<String> item = new TreeItem<>("文件" + (i + 1));
            rootItem.getChildren().add(item);
        }
        TreeView<String> treeView = new TreeView<>(rootItem);

        StackPane root = new StackPane(treeView);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setTitle("TreeView使用示例");
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