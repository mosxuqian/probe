package com.blinkfox.learn.javafx.archon.bean;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;

/**
 * git仓库信息的类
 * Created by blinkfox on 2017/3/21.
 */
public class GitInfo {

    // git对象
    private Git git;

    // repo对象
    private Repository repo;

    /**
     * 私有构造方法.
     */
    public GitInfo() {
        super();
    }

    /* getter 和 setter 方法 */
    public Git getGit() {
        return git;
    }

    public GitInfo setGit(Git git) {
        this.git = git;
        return this;
    }

    public Repository getRepo() {
        return repo;
    }

    public GitInfo setRepo(Repository repo) {
        this.repo = repo;
        return this;
    }

}