package com.blinkfox.learn.javafx.archon.controller;

import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_FILE_STATE;
import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_HISTORY;
import static com.blinkfox.learn.javafx.archon.consts.Constant.TEXT_WORK_SPACE;

import com.blinkfox.learn.javafx.archon.commons.AbstractController;
import com.blinkfox.learn.javafx.archon.model.CommitRecord;
import com.blinkfox.test.other.TimeUtils;
import com.blinkfox.zealot.helpers.StringHelper;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;
import org.pmw.tinylog.Logger;

/**
 * 主界面的控制器.
 * Created by blinkfox on 2017/3/24.
 */
public class MainController extends AbstractController {

    @FXML
    private TreeView<String> workTreeView; // 左侧工作空间树

    @FXML
    private TableView<CommitRecord> hisRecordTableView; // 历史提交记录table

    /* 历史记录各列数据 */
    @FXML
    private TableColumn<CommitRecord, String> shortMsgColumn;
    @FXML
    private TableColumn<CommitRecord, String> authorNameColumn;
    @FXML
    private TableColumn<CommitRecord, String> commitTimeColumn;
    @FXML
    private TableColumn<CommitRecord, String> shortCommitColumn;

    /* 被选中某一行历史记录表的数据之后显示的Label数据 */
    @FXML
    private Label hisAuthorLabel;
    @FXML
    private Label hisDateLabel;
    @FXML
    private Label hisCommitLabel;
    @FXML
    private Label hisMsgLabel;

    private static final String REPO = "F:\\gitrepo\\probe";

    // 可观察的 CommitRecord 集合
    private ObservableList<CommitRecord> commitRecords = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        initWorkTreeView();
        initHisRecordTable();
    }

    /**
     * 初始化workspace的TreeView.
     */
    private void initWorkTreeView() {
        // 创建根节点及子节点，并设置到TreeView中
        Node rootIcon = new ImageView(new Image(getClass()
                .getResourceAsStream("/javafx/image/workspace.png")));
        TreeItem<String> rootItem = new TreeItem<>(TEXT_WORK_SPACE, rootIcon);
        rootItem.setExpanded(true);
        TreeItem<String> fileStateItem = new TreeItem<>(TEXT_FILE_STATE);
        TreeItem<String> historyItem = new TreeItem<>(TEXT_HISTORY);
        ObservableList<TreeItem<String>> workItems = rootItem.getChildren();
        workItems.add(fileStateItem);
        workItems.add(historyItem);

        // 工作空间相关事件的操作
        workTreeView.setRoot(rootItem);
        workTreeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (TEXT_HISTORY.equals(newVal.getValue())) {
                Logger.info("开始读取git历史记录信息...");
                this.setHisRecordTableData();
            } else {
                Logger.info("显示文件状态的右侧列表数据...");
            }
        });
    }

    /**
     * 初始化历史记录表格相关数据.
     */
    private void initHisRecordTable() {
        this.hisRecordTableView.setItems(commitRecords);
        this.setHisRecordTableColumnData();
        listenSelectHisRecord();
    }

    /**
     * 设置历史提交记录列表的数据.
     */
    private void setHisRecordTableData() {
        try (Git git = Git.open(new File(REPO))) {
            Iterable<RevCommit> commits = git.log().call();
            if (commits == null) {
                return;
            }

            // 先清空集合信息，再设置到集合中
            commitRecords.clear();
            for (RevCommit commit : commits) {
                CommitRecord record = new CommitRecord(commit.getShortMessage(), commit.getFullMessage(),
                        commit.getAuthorIdent().getName(), commit.getAuthorIdent().getEmailAddress(),
                        TimeUtils.timeToStr(commit.getCommitTime()), commit.getName());
                commitRecords.add(record);
            }
            git.close();
        } catch (Exception e) {
            Logger.error(e, "读取git历史提交记录信息失败!");
        }
    }

    /**
     * 设置设置历史提交记录列表各列的数据..
     */
    private void setHisRecordTableColumnData() {
        shortMsgColumn.setCellValueFactory(cellData -> cellData.getValue().shortMsgProperty());
        authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().authorNameProperty());
        commitTimeColumn.setCellValueFactory(cellData -> cellData.getValue().commitTimeProperty());
        shortCommitColumn.setCellValueFactory(cellData -> cellData.getValue().shortCommitProperty());
    }

    /**
     * 监听某一行历史记录被选中时的操作.
     */
    private void listenSelectHisRecord() {
        hisRecordTableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> this.setHisSelectedLabel(newVal));
    }

    /**
     * 设置历史记录表格中，被选中一行的显示Label信息.
     */
    private void setHisSelectedLabel(CommitRecord record) {
        if (record != null) {
            hisAuthorLabel.setText(StringHelper.concat(record.getAuthorName(), "<", record.getAuthorEmail() , ">"));
            hisDateLabel.setText(record.getCommitTime());
            hisCommitLabel.setText(record.getFullCommit());
            hisMsgLabel.setText(record.getFullMsg());
        }
    }

}