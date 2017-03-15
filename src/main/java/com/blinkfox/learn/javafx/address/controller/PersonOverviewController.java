package com.blinkfox.learn.javafx.address.controller;

import com.blinkfox.learn.javafx.address.MainApp;
import com.blinkfox.learn.javafx.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    // MainApp的实例
    private MainApp mainApp;

    /**
     * 构造方法
     */
    public PersonOverviewController() {
        super();
    }

    /**
     * 初始化该Controller，当对应的fxml加载之后自动调用该方法
     */
    @FXML
    public void initialize() {
        familyNameColumn.setCellValueFactory(cellData -> cellData.getValue().familyNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        birthdayColumn.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
    }

    /**
     * 主应用程序调用
     * @param mainApp mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // 添加一个可观察的列表到table中
        personTable.setItems(mainApp.getPersons());
    }
}