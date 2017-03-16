package com.blinkfox.learn.javafx.address.controller;

import com.blinkfox.learn.javafx.address.MainApp;
import com.blinkfox.learn.javafx.address.model.Person;
import com.blinkfox.learn.javafx.address.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.pmw.tinylog.Logger;
import java.time.LocalDate;

/**
 * PersonOverview的控制器
 * Created by blinkfox on 2017/3/14.
 */
public class PersonOverviewController {

    // 表格信息
    @FXML
    private TableView<Person> personTable;

    /* 表格各列的信息 */
    @FXML
    private TableColumn<Person, String> familyNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, LocalDate> birthdayColumn;

    /* 单条联系人的详情信息 */
    @FXML
    private Label familyNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;

    // 按钮
    @FXML
    private Button delPersonBtn;

    /**
     * 主应用程序调用
     * @param mainApp mainApp
     */
    public void setMainApp(MainApp mainApp) {
        // 添加一个可观察的列表到table中
        personTable.setItems(mainApp.getPersons());
    }

    /**
     * 初始化该Controller，当对应的fxml加载之后自动调用该方法
     */
    @FXML
    public void initialize() {
        // 初始化三列的person信息
        familyNameColumn.setCellValueFactory(cellData -> cellData.getValue().familyNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());

        // 清空联系人详情
        showPersonDetail(null);

        // 监听某一行被选中时显示联系人详情信息
        personTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
            delPersonBtn.setDisable(false);
            showPersonDetail(newValue);
        });

        // 未选中时，禁用删除按钮
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex < 0) {
            delPersonBtn.setDisable(true);
        } else {
            delPersonBtn.setDisable(false);
        }
    }

    /**
     * 删除一个联系人的action操作
     */
    @FXML
    private void delPersonAction() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            Logger.warn("没有选中联系人，无法删除");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("温馨提示");
            alert.setHeaderText("无法删除联系人");
            alert.setContentText("你还没有选中联系人，无法删除，请选择您需要删除的联系人");
            alert.showAndWait();
        }
    }

    /**
     * 填充所有Label字段，用来显示用户详情信息
     * @param person Person实例
     */
    private void showPersonDetail(Person person) {
        if (person != null) {
            familyNameLabel.setText(person.getFamilyName());
            lastNameLabel.setText(person.getLastName());
            birthdayLabel.setText(DateUtils.getDateStr(person.getBirthday()));
            cityLabel.setText(person.getCity());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
        } else {
            familyNameLabel.setText("");
            lastNameLabel.setText("");
            birthdayLabel.setText("");
            cityLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
        }
    }

}