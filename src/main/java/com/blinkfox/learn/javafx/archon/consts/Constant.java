package com.blinkfox.learn.javafx.archon.consts;

/**
 * archon的常量类
 * Created by blinkfox on 2017-03-20.
 */
public final class Constant {

    /* start第一步 */
    public static final String STEP_ONE = "1";
    /* start第二步 */
    public static final String STEP_TWO = "2";
    /* start第三步 */
    public static final String STEP_THREE = "3";
    /* start第三步，初始化创建 */
    public static final String STEP_THREE_INIT = "3-1";
    /* start第三步，克隆创建 */
    public static final String STEP_THREE_CLONE = "3-2";
    /* start第三步，打开已有 */
    public static final String STEP_THREE_OPEN = "3-3";

    /* Git全局用户名的命令行常量 */
    public static final String GIT_USER_NAME = "git config --global user.name";
    /* Git全局用户邮箱的命令行常量 */
    public static final String GIT_USER_EMAIL = "git config --global user.email";

    /* Git默认工作目录的目录名称常量 */
    public static final String DEFAULT_GIT_DIR = "gitwork";

    /* 一些前台需要显示的常量，以 TEXT 开头 */
    public static final String TEXT_WORK_SPACE = "工作空间";
    public static final String TEXT_FILE_STATE = "文件状态";
    public static final String TEXT_HISTORY = "历史记录";

    /**
     * 私有构造方法.
     */
    private Constant() {
        super();
    }

}