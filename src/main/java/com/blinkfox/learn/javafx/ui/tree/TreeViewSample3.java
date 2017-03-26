package com.blinkfox.learn.javafx.ui.tree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * TreeView使用示例3.
 * Created by blinkfox on 2017/3/26.
 */
public class TreeViewSample3 extends Application {

    /**
     * start方法.
     * @param primaryStage 主stage
     */
    @Override
    public void start(Stage primaryStage) {
        // 根复选框树节点
        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>("查看资源文件");
        rootItem.setExpanded(true);

        // 生成子节点
        for (int i = 0; i < 6; i++) {
            final CheckBoxTreeItem<String> checkBoxTreeItem = new CheckBoxTreeItem<>("Sample" + (i + 1));
            rootItem.getChildren().add(checkBoxTreeItem);
        }

        // 生成TreeView，且使tree可复选框编辑
        final TreeView<String> tree = new TreeView<>(rootItem);
        tree.setEditable(true);
        tree.setCellFactory(CheckBoxTreeCell.forTreeView());
        tree.setRoot(rootItem);
        tree.setShowRoot(true);

        StackPane root = new StackPane();
        root.getChildren().add(tree);
        primaryStage.setTitle("Tree View使用示例3");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    /**
     * main方法.
     * @param args 数组参数.
     */
    public static void main(String[] args) {
        launch(args);
    }

}