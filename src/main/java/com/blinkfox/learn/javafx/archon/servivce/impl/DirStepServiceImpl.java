package com.blinkfox.learn.javafx.archon.servivce.impl;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import com.blinkfox.learn.javafx.archon.servivce.model.StepModel;
import java.io.File;

/**
 * 默认工作目录相关操作类（即第二步的具体操作类）.
 * Created by blinkfox on 2017-03-22.
 */
public class DirStepServiceImpl extends StepModel {

    /**
     * 初始化Git创建、读取仓库等操作的默认工作目录.
     */
    @Override
    public void init() {
        defaultWorkDir.setText(System.getProperty("user.home") + File.separator + Constant.DEFAULT_GIT_DIR);
    }

}