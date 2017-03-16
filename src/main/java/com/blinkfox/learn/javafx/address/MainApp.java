package com.blinkfox.learn.javafx.address;

import com.blinkfox.learn.javafx.address.controller.PersonEditController;
import com.blinkfox.learn.javafx.address.controller.PersonOverviewController;
import com.blinkfox.learn.javafx.address.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;
import java.io.IOException;

/**
 * 主运行类
 * Created by blinkfox on 2017/3/14.
 */
public class MainApp extends Application {

    // 主stage
    private Stage primaryStage;

    private BorderPane rootLayout;

    // 可观察的person集合
    private ObservableList<Person> persons = FXCollections.observableArrayList();

    /**
     * 构造器,初始化一些person数据
     */
    public MainApp() {
        persons.add(new Person("张", "三"));
        persons.add(new Person("李", "四"));
        persons.add(new Person("王", "五"));
        persons.add(new Person("马", "六"));
        persons.add(new Person("赵", "七"));
        persons.add(new Person("周", "八"));
        persons.add(new Person("孙", "九"));
        persons.add(new Person("郑", "十"));
    }

    /**
     * start
     * @param primaryStage start
     * @throws Exception e
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initRootLayout();
        showPersonOverview();
        primaryStage.setTitle("联系人管理应用");
        primaryStage.getIcons().add(new Image("/javafx/image/notebook.png"));
        primaryStage.show();
    }

    /**
     * 初始化rootLayout
     */
    private void initRootLayout() {
        try {
            // 加载RootLayout.fxml
            rootLayout = FXMLLoader.load(getClass().getResource("/javafx/fxml/RootLayout.fxml"));
            primaryStage.setScene(new Scene(rootLayout));
        } catch (IOException e) {
            Logger.error(e, "加载RootLayout.fxml文件失败");
        }
    }

    /**
     * 在根布局中显示联系人管理界面
     */
    private void showPersonOverview() {
        try {
            // 加载PersonOverview.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/javafx/fxml/PersonOverview.fxml"));
            AnchorPane personOverview = loader.load();
            rootLayout.setCenter(personOverview);

            // 给这个Controller设置MainApp实例
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            Logger.error(e, "加载PersonOverview.fxml文件失败");
        }
    }

    /**
     * 打开一个联系人信息编辑框，如果用户点击保存则保存联系人信息
     * @param person Person实例
     * @return boolean
     */
    public boolean showPersonEditDialog(Person person) {
        Stage editStage = new Stage();
        editStage.setTitle("编辑联系人信息");
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.initOwner(primaryStage);

        // 加载PersonOverview.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/javafx/fxml/PersonEdit.fxml"));
        AnchorPane personEditPane = null;
        try {
            personEditPane = loader.load();
            editStage.setScene(new Scene(personEditPane));
        } catch (IOException e) {
            Logger.error(e, "加载PersonEdit.fxml文件失败");
            return false;
        }

        PersonEditController controller = loader.getController();
        controller.setEditStage(editStage);
        controller.setPerson(person);
        editStage.showAndWait();
        return controller.isOnClicked();
    }

    /**
     * 得到主要的stage
     * @return stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * 得到可观察的person集合数据
     * @return persons
     */
    public ObservableList<Person> getPersons() {
        return persons;
    }

    /**
     * main方法
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}