package com.blinkfox.learn.jgit;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.pmw.tinylog.Logger;


/**
 * Git config相关的读取配置示例类.
 * Created by blinkfox on 2017-03-21.
 */
public class GitConfig {

    /**
     * 私有构造方法.
     */
    private GitConfig() {
        super();
    }

    /**
     * main方法.
     * @param args 数组参数
     */
    public static void main(String[] args) throws IOException {
        Repository repo = new RepositoryBuilder().findGitDir(new File("F:/code/gitrepo/ZFW_SACW")).build();
        Config config = repo.getConfig();
        Set<String> remotes = config.getSubsections("remote");
        for (String remote: remotes) {
            String url = config.getString("remote", remote, "url");
            Logger.info("远程仓库{}的url地址为:{}", remote, url);
        }

        String name = config.getString("user", null, "name");
        String email = config.getString("user", null, "email");
        Logger.info("仓库用户名user.name:{}, 邮箱user.email:{}", name, email);

        Repository repository = openRepository();
        Config envConfig = repository.getConfig();
        String envName = envConfig.getString("user", null, "name");
        String envEmail = envConfig.getString("user", null, "email");
        Logger.info("Git系统用户名user.name:{}, 邮箱user.email:{}", envName, envEmail);
    }

    /**
     * 读取系统环境中的仓库.
     * @return Repository
     * @throws IOException IO异常
     */
    private static Repository openRepository() throws IOException {
        return new FileRepositoryBuilder().readEnvironment().findGitDir().build();
    }

}
