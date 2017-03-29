package com.blinkfox.learn.javafx.archon.controller;

import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_FILE_STATE;
import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_HISTORY;
import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_WORK_SPACE;

import com.blinkfox.learn.javafx.archon.commons.AbstractController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.pmw.tinylog.Logger;

/**
 * 主界面的控制器.
 * Created by blinkfox on 2017/3/24.
 */
public class MainController extends AbstractController {

    @FXML
    private TreeView<String> workTreeView;

    @FXML
    private void initialize() {
        Logger.info("MainController初始化开始...");
        initWorkTreeView();
    }

    /**
     * 初始化workspace的TreeView.
     */
    private void initWorkTreeView() {
        // 创建根节点及子节点，并设置到TreeView中
        Node rootIcon = new ImageView(new Image(getClass()
                .getResourceAsStream("/javafx/image/workspace.png")));
        TreeItem<String> rootItem = new TreeItem<>(TEXT_WORK_SPACE, rootIcon);
        rootItem.setExpanded(true);
        TreeItem<String> fileStateItem = new TreeItem<>(TEXT_FILE_STATE);
        TreeItem<String> historyItem = new TreeItem<>(TEXT_HISTORY);
        ObservableList<TreeItem<String>> workItems = rootItem.getChildren();
        workItems.add(fileStateItem);
        workItems.add(historyItem);

        // 工作空间相关事件的操作
        workTreeView.setRoot(rootItem);
        workTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (TEXT_HISTORY.equals(newVal.getValue())) {
                Logger.info("显示历史记录的右侧列表数据...");
            } else {
                Logger.info("显示文件状态的右侧列表数据...");
            }
        });
    }

}