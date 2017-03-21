package com.blinkfox.learn.javafx.archon.core;

import com.blinkfox.learn.javafx.archon.bean.GitInfo;

/**
 * archon各种核心git仓库信息等的枚举单例类
 * Created by blinkfox on 2017/3/21.
 */
public enum ArchonDataContainer {

    INSTANCE;

    private GitInfo gitInfo;

    /**
     * 获取GitInfo对象.
     * @return GitInfo实例
     */
    public GitInfo getGitInfo() {
        return gitInfo;
    }

    /**
     * 设置GitInfo对象的值.
     * @param gitInfo GitInfo实例
     */
    public void setGitInfo(GitInfo gitInfo) {
        this.gitInfo = gitInfo;
    }

}