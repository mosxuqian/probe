package com.blinkfox.learn.javafx.address.utils;

import javafx.scene.control.Alert;

/**
 * JavaFx弹框的工具类
 * Created by blinkfox on 2017/3/16.
 */
public final class DialogUtils {

    /**
     * 私有构造方法
     */
    private DialogUtils() {
        super();
    }

    /**
     * 弹出警告提示框
     * @param header 头部信息
     * @param content 内容
     */
    public static void alertWarn(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("温馨提示");

        if (header != null) {
            alert.setHeaderText(header);
        }
        if (content != null) {
            alert.setContentText(content);
        }

        alert.showAndWait();
    }

    /**
     * 弹出普通提示信息框
     * @param title 标题
     * @param header 头部信息
     * @param content 内容
     */
    public static void alertInfo(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}