package com.blinkfox.learn.javafx.ui.tree;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.pmw.tinylog.Logger;

/**
 * JavaFx TreeView使用示例2.
 * Created by blinkfox on 2017/3/26.
 */
public class TreeViewSample2 extends Application {

    private final Node rootIcon = new ImageView(new Image(getClass()
            .getResourceAsStream("/javafx/image/root.png")));
    private static final Node deptIcon = new ImageView(new Image(TreeViewSample2.class
            .getResourceAsStream("/javafx/image/department.png")));
    private List<Employee> employees = Arrays.asList(
            new Employee("张三", "技术部"),
            new Employee("李思", "市场部"),
            new Employee("王五", "测试部"),
            new Employee("马六", "技术部"),
            new Employee("赵奇", "技术部"),
            new Employee("王八", "测试部"),
            new Employee("周九", "市场部"),
            new Employee("郑十", "市场部")
    );

    /**
     * start方法.
     * @param primaryStage 主stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 根节点
        TreeItem<String> rootNode = new TreeItem<>("公司员工分布", rootIcon);
        rootNode.setExpanded(true);

        // 遍历对人力资源按部门分组，生成对应的树节点
        for (Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> deptNode : rootNode.getChildren()) {
                if (deptNode.getValue().contentEquals(employee.getDepartment())) {
                    deptNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> deptNode = new TreeItem<>(employee.getDepartment(), deptIcon);
                rootNode.getChildren().add(deptNode);
                deptNode.getChildren().add(empLeaf);
            }
        }

        // 节点单机事件
        TreeView<String> treeView = new TreeView<>(rootNode);
        treeView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> Logger.info("旧值：{}, 新值：{}", oldVal, newVal));
        treeView.setEditable(true);
        treeView.setCellFactory(param -> new TextFieldTreeCell());

        VBox box = new VBox();
        box.getChildren().add(treeView);

        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTGRAY);
        primaryStage.setTitle("Tree View使用示例2");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) {
        launch(args);
    }

}