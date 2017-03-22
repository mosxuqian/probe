package com.blinkfox.learn.javafx.archon.commons;

import javafx.scene.Parent;

/**
 * JavaFx的Controller接口.
 * Created by blinkfox on 2017-03-22.
 */
public interface IController {

    /**
     * 获取view.
     * @return parent实例
     */
    Parent getView();

    /**
     * 设置view.
     * @param view parent实例
     */
    void setView(Parent view);

}