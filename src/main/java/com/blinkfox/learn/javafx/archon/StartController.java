package com.blinkfox.learn.javafx.archon;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    /* git账户、默认仓库和开始使用的三个label */
    @FXML
    private AnchorPane gitAccountPane;
    @FXML
    private AnchorPane defaultRepoPane;
    @FXML
    private AnchorPane startUsePane;

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

}