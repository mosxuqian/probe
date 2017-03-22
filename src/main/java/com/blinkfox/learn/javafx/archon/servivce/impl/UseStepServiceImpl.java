package com.blinkfox.learn.javafx.archon.servivce.impl;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import com.blinkfox.learn.javafx.archon.servivce.inter.IStepService;
import com.blinkfox.learn.javafx.archon.servivce.model.StepModel;

/**
 * 开始使用Git相关的操作类（即第三步的具体操作类）.
 * Created by blinkfox on 2017-03-22.
 */
public class UseStepServiceImpl extends StepModel implements IStepService {

    /**
     * 初始化时监听各种开始方式下的单选框事件.
     */
    @Override
    public void init() {
        startType.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            String newText = newValue.getUserData() == null ? "" : newValue.getUserData().toString();
            if (Constant.STEP_THREE_INIT.equals(newText)) {
                initRepoPane.setVisible(true);
                cloneRepoPane.setVisible(false);
                openRepoPane.setVisible(false);
                clearValidMsg();
            } else if (Constant.STEP_THREE_CLONE.equals(newText)) {
                initRepoPane.setVisible(false);
                cloneRepoPane.setVisible(true);
                openRepoPane.setVisible(false);
                clearValidMsg();
            } else if (Constant.STEP_THREE_OPEN.equals(newText)) {
                initRepoPane.setVisible(false);
                cloneRepoPane.setVisible(false);
                openRepoPane.setVisible(true);
                clearValidMsg();
            }
        });
    }

    /**
     * 清空校验的提示信息.
     */
    private void clearValidMsg() {
        validMsgLabel.setText("");
    }

}