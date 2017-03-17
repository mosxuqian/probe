package com.blinkfox.learn.javafx.address;

import com.blinkfox.learn.javafx.address.controller.BirthdayStatisController;
import com.blinkfox.learn.javafx.address.controller.PersonEditController;
import com.blinkfox.learn.javafx.address.controller.PersonOverviewController;
import com.blinkfox.learn.javafx.address.controller.RootLayoutController;
import com.blinkfox.learn.javafx.address.model.Person;
import com.blinkfox.learn.javafx.address.model.PersonsWrapper;
import com.blinkfox.learn.javafx.address.utils.DialogUtils;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.prefs.Preferences;

/**
 * 主运行类
 * Created by blinkfox on 2017/3/14.
 */
public class MainApp extends Application {

    // 主stage
    private Stage primaryStage;

    private BorderPane rootLayout;

    /* 配置文件key常量 */
    private static final String PREFS_FILE_PATH = "filePath";

    // 可观察的person集合
    private ObservableList<Person> persons = FXCollections.observableArrayList();

    /**
     * 构造器,初始化一些person数据
     */
    public MainApp() {
        persons.add(new Person("张", "三", LocalDate.of(1990, 2, 15)));
        persons.add(new Person("李", "四", LocalDate.of(1991, 3, 11)));
        persons.add(new Person("王", "五", LocalDate.of(1993, 3, 7)));
        persons.add(new Person("马", "六", LocalDate.of(1992, 3, 9)));
        persons.add(new Person("赵", "七", LocalDate.of(1989, 5, 2)));
        persons.add(new Person("周", "八", LocalDate.of(1991, 6, 3)));
        persons.add(new Person("孙", "九", LocalDate.of(1992, 6, 18)));
        persons.add(new Person("郑", "十", LocalDate.of(1993, 9, 2)));
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
        primaryStage.getIcons().add(new Image("/javafx/image/book.png"));

    }

    /**
     * 初始化rootLayout
     */
    private void initRootLayout() {
        try {
            // 加载RootLayout.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/javafx/fxml/RootLayout.fxml"));
            rootLayout = loader.load();
            primaryStage.setScene(new Scene(rootLayout));

            // 得到对应的控制器，并设置mainApp实例
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            Logger.error(e, "加载RootLayout.fxml文件失败");
        }

        // 加载最后打开的Person文件
        File file = this.getPersonFilePath();
        if (file != null) {
            this.loadPersonsFromFile(file);
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
     * 获取用户的注册文件信息
     * @return file
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get(PREFS_FILE_PATH, null);
        return filePath != null ? new File(filePath) : null;
    }

    /**
     * 设置当前加载的文件的文件路径。 该路径持久存储在操作系统特定的注册表中。
     * @param file file
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put(PREFS_FILE_PATH, file.getPath());
            primaryStage.setTitle("联系人应用-" + file.getName());
        } else {
            prefs.remove(PREFS_FILE_PATH);
            primaryStage.setTitle("联系人应用");
        }
    }

    /**
     * 从指定的文件加载Person数据，将替换当前的Persons数据。
     * @param file file
     */
    public void loadPersonsFromFile(File file) {
        PersonsWrapper personsWrapper = null;
        try {
            JAXBContext context = JAXBContext.newInstance(PersonsWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // 从XML文件中读取并反解析为Java对象
            personsWrapper = (PersonsWrapper) um.unmarshal(file);
        } catch (Exception e) {
            Logger.error(e, "加载联系人应用的注册表信息失败");
            Preferences.userNodeForPackage(MainApp.class).remove(PREFS_FILE_PATH);
            DialogUtils.alertWarn("加载联系人应用的注册表信息失败", "生成JAXBContext实例失败");
            return;
        }

        // 将文件中的person信息存到可观察列表中
        persons.clear();
        persons.addAll(personsWrapper.getPersons());

        // 保存文件到注册表中
        setPersonFilePath(file);
    }

    /**
     * 保持persons信息到注册表文件中
     * @param file file
     */
    public void savePersonsToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonsWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonsWrapper wrapper = new PersonsWrapper();
            wrapper.setPersons(persons);

            // 解析Pesons信息到XML文件中
            m.marshal(wrapper, file);

            // 保存文件到注册表中
            setPersonFilePath(file);
        } catch (JAXBException e) {
            Logger.error(e, "保持persons信息到注册表文件中失败");
            DialogUtils.alertWarn("保存联系人信息失败", "保持persons信息到注册表文件中失败");
        }
    }

    /**
     * 打开一个显示联系人生日月份统计的对话框
     */
    public void showBirthdayStatis() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/javafx/fxml/BirthdayStatis.fxml"));
            AnchorPane statisPane = loader.load();

            // 创建显示统计图的stage和scene
            Stage statisStage = new Stage();
            statisStage.setTitle("联系人生日月份统计图");
            statisStage.initModality(Modality.WINDOW_MODAL);
            statisStage.initOwner(primaryStage);
            statisStage.setScene(new Scene(statisPane));

            // 设置person集合数据到controller中并显示
            BirthdayStatisController controller = loader.getController();
            controller.setPersons(persons);
            statisStage.show();
        } catch (Exception e) {
            Logger.error(e, "显示联系人生日月份统计的对话框出错！");
            DialogUtils.alertWarn("统计失败", "显示联系人生日月份统计的对话框出错！");
        }
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