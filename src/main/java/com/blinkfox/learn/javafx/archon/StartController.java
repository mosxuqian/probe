package com.blinkfox.learn.javafx.archon;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import com.blinkfox.learn.javafx.archon.helpers.DialogHelper;
import com.blinkfox.learn.jgit.ExecCmdHelper;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * start界面的控制器.
 * Created by blinkfox on 2017-03-20.
 */
public class StartController {

    /* 上一步、下一步和隐藏文本字段的控件 */
    @FXML
    private Button prevStepBtn;
    @FXML
    private Button nextStepBtn;
    @FXML
    private TextField hideStepField;

    /* 三步Label标签的控件 */
    @FXML
    private HBox gitAccountBox;
    @FXML
    private HBox defaultRepoBox;
    @FXML
    private HBox startUseBox;

    /* git账户、默认仓库和开始使用的三个label控件 */
    @FXML
    private AnchorPane gitAccountPane;
    @FXML
    private AnchorPane defaultRepoPane;
    @FXML
    private AnchorPane startUsePane;

    /* 三个开始使用radio对应的Pane和toggleGroup的控件 */
    @FXML
    private ToggleGroup startType;
    @FXML
    private GridPane initRepoPane;
    @FXML
    private GridPane cloneRepoPane;
    @FXML
    private GridPane openRepoPane;

    @FXML
    private TextField userNameField;
    @FXML
    private TextField userEmailField;
    @FXML
    private TextField defaultWorkDir;

    /* 选中时的样式名称 */
    private static final String SELECTED_CSS_CLASS = "selectedBox";

    /**
     * 初始化时的相关操作.
     */
    @FXML
    private void initialize() {
        /* 一些初始化操作,分别是初始化Git用户信息、开始类型选中事件监听等 */
        initGlobalUserInfo();
        initDefaultWorkDir();
        listenStartTypeRadio();
    }

    /**
     * 初始化全局的Git用户信息.
     */
    private void initGlobalUserInfo() {
        // 通过命令行来获取git全局的用户信息，如果不为空则为其设置默认值
        String userName = ExecCmdHelper.execCmd(Constant.GIT_USER_NAME);
        String userEmail = ExecCmdHelper.execCmd(Constant.GIT_USER_EMAIL);
        if (userName != null && !userName.isEmpty()) {
            userNameField.setText(userName);
        }
        if (userEmail != null && !userEmail.isEmpty()) {
            userEmailField.setText(userEmail);
        }
    }

    /**
     * 初始化Git创建、读取仓库等操作的默认工作目录.
     */
    private void initDefaultWorkDir() {
        defaultWorkDir.setText(System.getProperty("user.home") + File.separator + Constant.DEFAULT_GIT_DIR);
    }

    /**
     * 监听各种开始方式下的单选框事件.
     */
    private void listenStartTypeRadio() {
        startType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            String newText = newValue.getUserData() == null ? "" : newValue.getUserData().toString();
            if (Constant.STEP_THREE_INIT.equals(newText)) {
                initRepoPane.setVisible(true);
                cloneRepoPane.setVisible(false);
                openRepoPane.setVisible(false);
            } else if (Constant.STEP_THREE_CLONE.equals(newText)) {
                initRepoPane.setVisible(false);
                cloneRepoPane.setVisible(true);
                openRepoPane.setVisible(false);
            } else if (Constant.STEP_THREE_OPEN.equals(newText)) {
                initRepoPane.setVisible(false);
                cloneRepoPane.setVisible(false);
                openRepoPane.setVisible(true);
            }
        });
    }

    /**
     * 下一步的相关操作.
     */
    @FXML
    private void nextStepAction() {
        String step = hideStepField.getText();
        if (Constant.STEP_ONE.equals(step)) {
            gitAccountBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            defaultRepoBox.getStyleClass().add(SELECTED_CSS_CLASS);
            gitAccountPane.setVisible(false);
            defaultRepoPane.setVisible(true);
            prevStepBtn.setVisible(true);
            hideStepField.setText(Constant.STEP_TWO);
        } else if (Constant.STEP_TWO.equals(step)) {
            defaultRepoBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            startUseBox.getStyleClass().add(SELECTED_CSS_CLASS);
            defaultRepoPane.setVisible(false);
            startUsePane.setVisible(true);
            nextStepBtn.setText("完成");
            hideStepField.setText(Constant.STEP_THREE_INIT);
        }
    }

    /**
     * 上一步相关操作.
     */
    @FXML
    private void prevStepAction() {
        String step = hideStepField.getText();
        if (Constant.STEP_TWO.equals(step)) {
            gitAccountBox.getStyleClass().add(SELECTED_CSS_CLASS);
            defaultRepoBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            defaultRepoPane.setVisible(false);
            gitAccountPane.setVisible(true);
            prevStepBtn.setVisible(false);
            hideStepField.setText(Constant.STEP_ONE);
        } else if (Constant.STEP_THREE_INIT.equals(step)
                || Constant.STEP_THREE_CLONE.equals(step)
                || Constant.STEP_THREE_OPEN.equals(step)) {
            defaultRepoBox.getStyleClass().add(SELECTED_CSS_CLASS);
            startUseBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            startUsePane.setVisible(false);
            defaultRepoPane.setVisible(true);
            nextStepBtn.setText("下一步");
            hideStepField.setText(Constant.STEP_TWO);
        }
    }

    /**
     * 选择默认的Git工作目录.并将选择的值赋给文本框
     */
    @FXML
    private void chooseDefaultWorkDir() {
        DirectoryChooser chooser = DialogHelper.createDirChooser();
        File file = chooser.showDialog(ArchonApplication.getPrimaryStage());
        if (file != null) {
            defaultWorkDir.setText(file.getAbsolutePath());
        }
    }

}