package com.blinkfox.learn.javafx.address.controller;

import com.blinkfox.learn.javafx.address.model.Person;
import com.blinkfox.zealot.helpers.StringHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 联系人信息编辑控制器
 * Created by blinkfox on 2017-03-16.
 */
public class PersonEditController {

    @FXML
    private TextField familyNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;

    private Stage editStage;
    private Person person;
    private boolean onClicked = false;

    /**
     * 设置editStage的值
     * @param editStage editStage
     */
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    /**
     * 初始化编辑时设置Person的值
     * @param person person
     */
    public void setPerson(Person person) {
        this.person = person;

        // 设置person的原来的值
        familyNameField.setText(person.getFamilyName());
        lastNameField.setText(person.getLastName());
        birthdayField.setValue(person.getBirthday());
        cityField.setText(person.getCity());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
    }

    /**
     * 判断按钮是否已经点击了
     * @return boolean
     */
    public boolean isOnClicked() {
        return onClicked;
    }

    @FXML
    private void init() {

    }

    /**
     * 是否是Integer类型的
     * @return boolean
     */
    private boolean isInteger(String str) {
        return str.matches("[0-9]+");
    }

    /**
     * 判断编辑的表单值是否有效
     * @return boolean
     */
    private boolean isEditValid() {
        StringBuilder errMsg = new StringBuilder("");
        if (StringHelper.isBlank(familyNameField.getText())) {
            errMsg.append("联系人姓氏为空！\n");
        }
        if (StringHelper.isBlank(lastNameField.getText())) {
            errMsg.append("联系人名字为空！\n");
        }
        if (birthdayField.getValue() == null) {
            errMsg.append("联系人生日为空！\n");
        }
        if (StringHelper.isBlank(cityField.getText())) {
            errMsg.append("联系人所在的城市为空！\n");
        }
        if (StringHelper.isBlank(streetField.getText())) {
            errMsg.append("联系人所在的街道地址为空！\n");
        }

        // 邮编判断
        String postalCode = postalCodeField.getText();
        if (StringHelper.isBlank(postalCode) && (postalCode.length() != 6) && (!isInteger(postalCode))) {
            errMsg.append("联系人的邮编不是有效的数字");
        }

        if (errMsg.toString().length() == 0) {
            return true;
        }

        // 警告提示框
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("温馨提示");
        alert.setContentText(errMsg.toString());
        alert.showAndWait();
        return false;
    }

    /**
     * 保存联系人信息的操作
     */
    @FXML
    public void savePersonAction() {
        if (!isEditValid()) {
            return;
        }

        // 将编辑后的值保存到person中
        person.setFamilyName(familyNameField.getText());
        person.setLastName(lastNameField.getText());
        person.setBirthday(birthdayField.getValue());
        person.setCity(cityField.getText());
        person.setStreet(streetField.getText());
        person.setPostalCode(Integer.valueOf(postalCodeField.getText()));

        onClicked = true;
        editStage.close();
    }

    /**
     * 取消按钮的操作
     */
    @FXML
    public void cancelAction() {
        editStage.close();
    }

}