package com.blinkfox.learn.javafx.archon.commons;

/**
 * 控制器工厂接口.
 * Created by blinkfox on 2017-03-22.
 */
public interface IControllerFactory {

    /**
     * 加载 FXML 文件.
     * @param fxmlFile FXML文件
     * @return IController实现实例
     */
    IController loadController(String fxmlFile);

}