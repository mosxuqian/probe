package com.blinkfox.learn.javafx.archon.controller;

import com.blinkfox.learn.javafx.archon.commons.AbstractController;
import javafx.fxml.FXML;
import org.pmw.tinylog.Logger;

/**
 * 主界面的控制器.
 * Created by blinkfox on 2017/3/24.
 */
public class MainController extends AbstractController {

    @FXML
    private void initialize() {
        Logger.info("MainController初始化开始...");
    }

}