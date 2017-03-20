package com.blinkfox.learn.javafx.archon;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.pmw.tinylog.Logger;

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

    /**
     * 初始化时的相关操作.
     */
    @FXML
    private void initialize() {
        Logger.info("开始初始化加载...");

        /* 监听第三步中的三个radio的change事件，好切换界面显示 */
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
            gitAccountPane.setVisible(false);
            defaultRepoPane.setVisible(true);
            prevStepBtn.setVisible(true);
            hideStepField.setText(Constant.STEP_TWO);
        } else if (Constant.STEP_TWO.equals(step)) {
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
            defaultRepoPane.setVisible(false);
            gitAccountPane.setVisible(true);
            prevStepBtn.setVisible(false);
            hideStepField.setText(Constant.STEP_ONE);
        } else if (Constant.STEP_THREE_INIT.equals(step)
                || Constant.STEP_THREE_CLONE.equals(step)
                || Constant.STEP_THREE_OPEN.equals(step)) {
            startUsePane.setVisible(false);
            defaultRepoPane.setVisible(true);
            nextStepBtn.setText("下一步");
            hideStepField.setText(Constant.STEP_TWO);
        }
    }

    /**
     * 初始化仓库时的操作.
     */
    @FXML
    private void initRepoAction() {

    }

    /**
     * 克隆远程仓库时的操作.
     */
    @FXML
    private void cloneRepoAction() {

    }

    /**
     * 打开已有仓库时的操作.
     */
    @FXML
    private void openRepoAction() {

    }

}