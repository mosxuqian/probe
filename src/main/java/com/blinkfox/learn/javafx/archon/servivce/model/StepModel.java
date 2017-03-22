package com.blinkfox.learn.javafx.archon.servivce.model;

import com.blinkfox.learn.javafx.archon.servivce.inter.IStepService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * 各步操作相关的Fxml model 对象,便于其他层次的对象调用.
 * Created by blinkfox on 2017-03-22.
 */
public abstract class StepModel implements IStepService {

    // Git全局用户名的文本框
    protected TextField userNameField;

    // Git全局邮箱的文本框
    protected TextField userEmailField;

    // 默认工作目录的文本框
    protected TextField defaultWorkDir;

    /* 三个开始使用radio对应的Pane和toggleGroup的控件 */
    protected ToggleGroup startType;
    protected GridPane initRepoPane;
    protected GridPane cloneRepoPane;
    protected GridPane openRepoPane;

    // 校验提示信息Label
    protected Label validMsgLabel;

    /* setter 方法 */
    public StepModel setUserNameField(TextField userNameField) {
        this.userNameField = userNameField;
        return this;
    }

    public StepModel setUserEmailField(TextField userEmailField) {
        this.userEmailField = userEmailField;
        return this;
    }

    public StepModel setDefaultWorkDir(TextField defaultWorkDir) {
        this.defaultWorkDir = defaultWorkDir;
        return this;
    }

    public StepModel setStartType(ToggleGroup startType) {
        this.startType = startType;
        return this;
    }

    public StepModel setInitRepoPane(GridPane initRepoPane) {
        this.initRepoPane = initRepoPane;
        return this;
    }

    public StepModel setCloneRepoPane(GridPane cloneRepoPane) {
        this.cloneRepoPane = cloneRepoPane;
        return this;
    }

    public StepModel setOpenRepoPane(GridPane openRepoPane) {
        this.openRepoPane = openRepoPane;
        return this;
    }

    public StepModel setValidMsgLabel(Label validMsgLabel) {
        this.validMsgLabel = validMsgLabel;
        return this;
    }

}