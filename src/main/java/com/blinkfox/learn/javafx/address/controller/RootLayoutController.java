package com.blinkfox.learn.javafx.address.controller;

import com.blinkfox.learn.javafx.address.MainApp;
import com.blinkfox.learn.javafx.address.utils.DialogUtils;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

import static java.lang.System.exit;

/**
 * 根布局控制器
 * Created by blinkfox on 2017/3/16.
 */
public class RootLayoutController {

    private MainApp mainApp;

    /**
     * mainApp的setter方法
     * @param mainApp mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * 显示保存文件对话框，并得到选中的文件
     * @return file
     */
    private FileChooser getXmlFileChooser() {
        // 得到文件选择器，并设置过滤.xml的扩展名
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

    /**
     * 菜单中"文件->新建"的操作
     */
    @FXML
    private void onFileNewAction() {
        mainApp.getPersons().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * 菜单中"文件->打开"的操作
     * 打开FileChooser，让用户选择要加载的通讯簿。
     */
    @FXML
    private void onFileOpenAction() {
        //新建文件选择框，并设置文件过滤
        File file = this.getXmlFileChooser().showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            mainApp.loadPersonsFromFile(file);
        }
    }

    /**
     * 菜单中"文件->打开"的操作
     * 将文件保存到当前打开的pserson文件。如果没有打开的文件，将显示“另存为”对话框。
     */
    @FXML
    private void onFileSaveAction() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonsToFile(personFile);
        } else {
            this.onFileSaveAsAction();
        }
    }

    /**
     * 菜单中"文件->打开"的操作
     * 打开FileChooser，让用户选择要保存的文件。
     */
    @FXML
    private void onFileSaveAsAction() {
        // 获取保存文件对话框及保存的文件
        File file = this.getXmlFileChooser().showSaveDialog(mainApp.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonsToFile(file);
        }
    }

    /**
     * 菜单中"文件->打开"的操作
     */
    @FXML
    private void onFileExitAction() {
        exit(0);
    }

    /**
     * 菜单中"统计->生日统计"的操作
     */
    @FXML
    private void onBirthdayStatisAction() {
        mainApp.showBirthdayStatis();
    }

    /**
     * 菜单中"文件->打开"的操作
     */
    @FXML
    private void onHelpAboutAction() {
        DialogUtils.alertInfo("关于我们", "I'm Blinkfox!", "Hello,欢迎您的使用!");
    }

}