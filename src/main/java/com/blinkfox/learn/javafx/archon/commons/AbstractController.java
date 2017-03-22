package com.blinkfox.learn.javafx.archon.commons;

import java.util.Objects;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * 实现了IController接口的抽象Controller类.
 * Created by blinkfox on 2017-03-22.
 */
public class AbstractController implements IController {

    private Parent view;

    /**
     * 获取view.
     * @return parent实例
     */
    @Override
    public Parent getView() {
        if (this.view == null) {
            throw new IllegalStateException("view是null,未成功设置view");
        }
        return view;
    }

    /**
     * 设置view.
     * @param view parent实例
     */
    @Override
    public void setView(Parent view) {
        if (this.view != null) {
            throw new IllegalStateException("view已经设值了!");
        }
        this.view = Objects.requireNonNull(view, "view不能为空null");
    }

    /**
     * 得到stage.
     * @return stage
     */
    protected Stage getStage() {
        return (Stage) view.getScene().getWindow();
    }

}