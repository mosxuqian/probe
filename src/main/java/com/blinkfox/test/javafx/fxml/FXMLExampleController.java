package com.blinkfox.test.javafx.fxml;

import com.blinkfox.utils.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller类
 * Created by blinkfox on 2017-03-13.
 */
public class FXMLExampleController implements Initializable {

    private static final Log log = Log.get(FXMLExampleController.class);

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        log.info("你点击了我！");
        label.setText("你好，JavaFx fxml!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("调用了initialize方法。。。");
    }

}