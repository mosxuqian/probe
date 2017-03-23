package com.blinkfox.learn.javafx.archon.controller;

import com.blinkfox.learn.javafx.archon.ArchonApplication;
import com.blinkfox.learn.javafx.archon.commons.AbstractController;
import com.blinkfox.learn.javafx.archon.commons.IControllerFactory;
import com.blinkfox.learn.javafx.archon.consts.Constant;
import com.blinkfox.learn.javafx.archon.helpers.DialogHelper;
import com.blinkfox.learn.javafx.archon.helpers.FileHelper;
import com.blinkfox.learn.javafx.archon.servivce.impl.AccountStepServiceImpl;
import com.blinkfox.learn.javafx.archon.servivce.impl.DirStepServiceImpl;
import com.blinkfox.learn.javafx.archon.servivce.impl.UseStepServiceImpl;
import com.blinkfox.learn.jgit.ExecCmdHelper;
import com.blinkfox.zealot.helpers.StringHelper;
import com.google.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.pmw.tinylog.Logger;

/**
 * start界面的控制器.
 * Created by blinkfox on 2017-03-20.
 */
public class StartController extends AbstractController {

    /* 上一步、下一步和隐藏文本字段的控件 */
    @FXML
    private Button prevStepBtn;
    @FXML
    private Button nextStepBtn;
    @FXML
    private TextField hideStepField;

    /* 三步Label标签的控件 */
    @FXML
    private HBox gitAccountBox;
    @FXML
    private HBox defaultRepoBox;
    @FXML
    private HBox startUseBox;

    /* git账户、默认仓库和开始使用的三个label控件 */
    @FXML
    private AnchorPane gitAccountPane;
    @FXML
    private AnchorPane defaultRepoPane;
    @FXML
    private AnchorPane startUsePane;

    /* 三个开始使用radio对应的Pane和toggleGroup的控件 */
    @FXML
    private ToggleGroup startType;
    @FXML
    private GridPane initRepoPane;
    @FXML
    private GridPane cloneRepoPane;
    @FXML
    private GridPane openRepoPane;

    /* 各表单TextField的控件 */
    @FXML
    private TextField userNameField;
    @FXML
    private TextField userEmailField;
    @FXML
    private TextField defaultWorkDir;
    @FXML
    private TextField initDirField;
    @FXML
    private TextField initNameField;
    @FXML
    private TextField cloneDirField;
    @FXML
    private TextField cloneUrlField;
    @FXML
    private TextField openDirField;
    @FXML
    private Label validMsgLabel; // 校验提示信息Label

    /* 选中时的样式名称 */
    private static final String SELECTED_CSS_CLASS = "selectedBox";

    @Inject
    private IControllerFactory controllerFactory;

    @Inject
    private AccountStepServiceImpl accountStepService;

    @Inject
    private DirStepServiceImpl dirStepService;

    @Inject
    private UseStepServiceImpl useStepService;

    /**
     * 初始化时的相关操作.
     */
    @FXML
    private void initialize() {
        /* 一些初始化操作,分别是初始化Git用户信息、默认工作空间、开始类型选中事件监听等 */
        accountStepService.setUserNameField(userNameField).setUserEmailField(userEmailField);
        accountStepService.init();

        dirStepService.setDefaultWorkDir(defaultWorkDir).init();

        useStepService.setStartType(startType).setInitRepoPane(initRepoPane).setCloneRepoPane(cloneRepoPane)
                .setOpenRepoPane(openRepoPane).setValidMsgLabel(validMsgLabel);
        useStepService.init();
    }

    /**
     * 下一步的相关操作.
     */
    @FXML
    private void nextStepAction() {
        String step = hideStepField.getText();
        if (Constant.STEP_ONE.equals(step)) {
            gitAccountBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            defaultRepoBox.getStyleClass().add(SELECTED_CSS_CLASS);
            gitAccountPane.setVisible(false);
            defaultRepoPane.setVisible(true);
            prevStepBtn.setVisible(true);
            hideStepField.setText(Constant.STEP_TWO);
        } else if (Constant.STEP_TWO.equals(step)) {
            defaultRepoBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            startUseBox.getStyleClass().add(SELECTED_CSS_CLASS);
            defaultRepoPane.setVisible(false);
            startUsePane.setVisible(true);
            nextStepBtn.setText("完成");
            hideStepField.setText(Constant.STEP_THREE_INIT);

            // 如果默认的Git工作空间目录不为空，则创建目录且将默认的路径赋给初始化时的目录
            String workDir = defaultWorkDir.getText();
            if (StringHelper.isNotBlank(workDir)) {
                FileHelper.makeDirs(workDir);
                initDirField.setText(workDir);
                cloneDirField.setText(workDir);
            }
        } else if (isThirdStep(step)) {
            // 如果是第三步，则开始校验数据和使用rchon
            validAndStartUse();
        }
    }

    /**
     * 上一步相关操作.
     */
    @FXML
    private void prevStepAction() {
        String step = hideStepField.getText();
        if (Constant.STEP_TWO.equals(step)) {
            gitAccountBox.getStyleClass().add(SELECTED_CSS_CLASS);
            defaultRepoBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            defaultRepoPane.setVisible(false);
            gitAccountPane.setVisible(true);
            prevStepBtn.setVisible(false);
            hideStepField.setText(Constant.STEP_ONE);
        } else if (isThirdStep(step)) {
            defaultRepoBox.getStyleClass().add(SELECTED_CSS_CLASS);
            startUseBox.getStyleClass().remove(SELECTED_CSS_CLASS);
            startUsePane.setVisible(false);
            defaultRepoPane.setVisible(true);
            nextStepBtn.setText("下一步");
            hideStepField.setText(Constant.STEP_TWO);
        }
    }

    /**
     * 选择默认的Git工作目录.并将选择的值赋给文本框
     */
    @FXML
    private void chooseDefaultWorkDir() {
        DirectoryChooser chooser = DialogHelper.createDirChooser();
        File file = chooser.showDialog(ArchonApplication.getPrimaryStage());
        if (file != null) {
            defaultWorkDir.setText(file.getAbsolutePath());
        }
    }

    /**
     * 选择新初始化的Git仓库目录.
     */
    @FXML
    private void chooseInitDir() {
        setChooseText(initDirField);
    }

    /**
     * 选择新初始化的Git仓库目录.
     */
    @FXML
    private void chooseCloneDir() {
        setChooseText(cloneDirField);
    }

    /**
     * 选择新初始化的Git仓库目录.
     */
    @FXML
    private void chooseOpenDir() {
        setChooseText(openDirField);
    }

    /**
     * 设置选择文件夹后框中的值.
     * @param field 文本框对象
     */
    private void setChooseText(TextField field) {
        File file = this.openDefaultDirChooser();
        if (file != null) {
            field.setText(file.getAbsolutePath());
        }
    }

    /**
     * 打开默认的git工作目录选择框.
     * @return file
     */
    private File openDefaultDirChooser() {
        // 打开Git仓库文件目录选择窗口
        DirectoryChooser chooser = DialogHelper.createDirChooser(defaultWorkDir.getText());
        return chooser.showDialog(ArchonApplication.getPrimaryStage());
    }

    /**
     * 判断是否是第三步了.
     * @param step 开始的step字符串
     * @return 布尔
     */
    private boolean isThirdStep(String step) {
        return Constant.STEP_THREE_INIT.equals(step)
                || Constant.STEP_THREE_CLONE.equals(step)
                || Constant.STEP_THREE_OPEN.equals(step);
    }

    /**
     * 校验和使用Archon.
     */
    private void validAndStartUse() {
        // 获取用户选择的具体使用方式步骤
        Object stepObj = startType.getSelectedToggle().getUserData();
        String step = stepObj == null ? Constant.STEP_THREE_INIT : stepObj.toString();
        if (Constant.STEP_THREE_INIT.equals(step)) {
            String dirText = initDirField.getText();
            if (StringHelper.isBlank(dirText)) {
                validMsgLabel.setText("新Git仓库所在的目录不能为空！");
                return;
            }

            String nameText = initNameField.getText();
            if (StringHelper.isBlank(nameText)) {
                validMsgLabel.setText("新Git仓库的名称不能为空！");
                return;
            }

            startInitUse(StringHelper.concat(dirText, File.separator, nameText));
        } else if (Constant.STEP_THREE_CLONE.equals(step)) {
            String dirText = cloneDirField.getText();
            if (StringHelper.isBlank(dirText)) {
                validMsgLabel.setText("将要克隆仓库的所在目录不能为空！");
                return;
            }

            String url = cloneUrlField.getText();
            if (StringHelper.isBlank(url)) {
                validMsgLabel.setText("远程仓库的URL地址不能为空！");
                return;
            }

            startCloneUse(StringHelper.concat(dirText, File.separator, parseGitNameByUrl(url)), url);
        } else if (Constant.STEP_THREE_OPEN.equals(step)) {
            String dirText = openDirField.getText();
            if (StringHelper.isBlank(dirText)) {
                validMsgLabel.setText("所要打开的Git仓库目录不能为空！");
                return;
            }
            startOpenUse(dirText);
        }
    }

    /**
     * 从.git的url中提取出git仓库名称.
     * 如：输入url为:https://github.com/blinkfox/zealot.git,输出zealot
     * @param url git的url地址
     * @return git仓库名称
     */
    private String parseGitNameByUrl(String url) {
        if (url == null) {
            return "";
        }
        String gitName = url.substring(url.lastIndexOf('/') + 1);
        return gitName.substring(0, gitName.lastIndexOf(".git"));
    }

    /**
     * 保存Git的全局账户信息.
     */
    private void saveGitAccount() {
        String userName = userNameField.getText();
        String userEmail = userEmailField.getText();
        if (StringHelper.isNotBlank(userName)) {
            ExecCmdHelper.execCmd(StringHelper.concat(Constant.GIT_USER_NAME, " ", userName));
        }
        if (StringHelper.isNotBlank(userEmail)) {
            ExecCmdHelper.execCmd(StringHelper.concat(Constant.GIT_USER_EMAIL, " ", userEmail));
        }
    }

    /**
     * 保存默认的Git工作空间路径.
     */
    private void saveDefaultWorkDir() {
        Logger.info("开始持久化默认的Git工作目录...");
    }

    /**
     * 开始初始化仓库并存储Git仓库信息等到DataContainer单例实例中.
     */
    private void startInitUse(String dirPath) {
        saveGitAccount();
        saveDefaultWorkDir();
        try {
            Git git = Git.init().setDirectory(new File(dirPath)).call();
            git.close();
        } catch (GitAPIException e) {
            Logger.error(e, "初始化Git仓库失败");
        }
    }

    /**
     * 开始初始化仓库并存储Git仓库信息等到DataContainer单例实例中.
     * @param dirPath 新仓库目录全路径(含Git仓库名)
     * @param url clone的URL地址
     */
    private void startCloneUse(String dirPath, String url) {
        saveGitAccount();
        saveDefaultWorkDir();
        try {
            Git git = Git.cloneRepository().setURI(url)
                    .setDirectory(new File(dirPath))
                    .setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out))).call();
            git.close();
        } catch (GitAPIException e) {
            Logger.error(e, "cloneGit仓库失败");
        }
    }

    /**
     * 开始初始化仓库并存储Git仓库信息等到DataContainer单例实例中.
     * @param dirPath 要打开仓库的全路径(含.git标识)
     */
    private void startOpenUse(String dirPath) {
        saveGitAccount();
        saveDefaultWorkDir();
        try {
            Git git = Git.open(new File(dirPath));
            git.close();
        } catch (IOException e) {
            Logger.error(e, "cloneGit仓库失败");
        }
    }

}