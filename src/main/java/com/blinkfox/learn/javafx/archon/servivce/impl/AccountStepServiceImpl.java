package com.blinkfox.learn.javafx.archon.servivce.impl;

import com.blinkfox.learn.javafx.archon.consts.Constant;
import com.blinkfox.learn.javafx.archon.servivce.model.StepModel;
import com.blinkfox.learn.jgit.ExecCmdHelper;

/**
 * 账户信息的相关操作的实现类（即第一步的具体操作类）.
 * Created by blinkfox on 2017-03-22.
 */
public class AccountStepServiceImpl extends StepModel {

    /**
     * 初始化全局的Git账户信息.
     */
    @Override
    public void init() {
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

}