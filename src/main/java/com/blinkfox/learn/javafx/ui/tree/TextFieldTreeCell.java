package com.blinkfox.learn.javafx.ui.tree;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyCode;
import org.pmw.tinylog.Logger;

/**
 * tree cell.
 * Created by blinkfox on 2017/3/26.
 */
class TextFieldTreeCell extends TreeCell<String> {

    private TextField textField;

    private final ContextMenu contextMenu = new ContextMenu();

    /**
     * 构造方法.
     * 添加右键菜单添加雇员的功能
     */
    TextFieldTreeCell() {
        MenuItem addMenuItem = new MenuItem("添加员工");
        contextMenu.getItems().add(addMenuItem);
        addMenuItem.setOnAction(event -> {
            TreeItem<String> newEmployee = new TreeItem<>("新员工");
            getTreeItem().getChildren().add(newEmployee);
        });
    }

    /**
     * 开始编辑时的方法.
     */
    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            Logger.info("textField is null");
            createTextField();
        }

        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    /**
     * 取消编辑.
     */
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setGraphic(getTreeItem().getGraphic());
    }

    /**
     * 更新节点.
     * @param item item
     * @param empty 是否为空
     */
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if ((!getTreeItem().isLeaf()) && (getTreeItem().getParent() != null)) {
                    setContextMenu(contextMenu);
                }
            }
        }
    }

    /**
     * 创建textField.
     * 如果键入enter，则提交编辑结果，如果是ESC，则取消编辑.
     */
    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    /**
     * 将item转成字符串.
     * @return string
     */
    private String getString() {
        return getItem() == null ? "" : getItem();
    }

}