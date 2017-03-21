package com.blinkfox.learn.javafx.archon.bean;

import org.eclipse.jgit.lib.Repository;

/**
 * git仓库信息的类
 * Created by blinkfox on 2017/3/21.
 */
public class GitInfo {

    // repo对象
    private Repository repo;

    /**
     * 私有构造方法.
     */
    public GitInfo() {
        super();
    }

    /* getter 和 setter 方法 */
    public Repository getRepo() {
        return repo;
    }

    public GitInfo setRepo(Repository repo) {
        this.repo = repo;
        return this;
    }

}