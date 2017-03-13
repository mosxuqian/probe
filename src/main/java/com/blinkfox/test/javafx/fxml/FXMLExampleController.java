package com.blinkfox.test.javafx.fxml;

import com.blinkfox.utils.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller类
 * Created by blinkfox on 2017-03-13.
 */
public class FXMLExampleController implements Initializable {

    private static final Log log = Log.get(FXMLExampleController.class);

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text actionTarget;

    /**
     * 登录事件，处理登录
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        log.info("账户名：" + userName.getText() + ",密码是：" + passwordField.getText());

        // 登录判断
        if ("blinkfox".equals(userName.getText()) && "123456".equals(passwordField.getText())) {
            actionTarget.setText("登录成功！");
        } else {
            actionTarget.setText("登录失败！");
        }
    }

    /**
     * 初始化方法
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("调用了initialize方法。。。");
    }

}