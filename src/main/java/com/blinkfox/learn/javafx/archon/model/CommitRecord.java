package com.blinkfox.learn.javafx.archon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 提交记录信息 JavaFx model 类.
 * Created by blinkfox on 2017-03-29.
 */
public class CommitRecord {

    // 短提交信息
    private StringProperty shortMsg;

    // 全提交信息
    private StringProperty fullMsg;

    // 提交作者的名称
    private StringProperty authorName;

    // 提交作者的邮箱
    private StringProperty authorEmail;

    // 提交时间的字符串
    private StringProperty commitTime;

    // 短提交hash,只有7位
    private StringProperty shortCommit;

    // 全提交hash,共40位
    private StringProperty fullCommit;

    /**
     * 默认的空构造方法.
     */
    public CommitRecord() {
        super();
    }

    /**
     * 全构造方法.
     * @param shortMsg 短提交信息
     * @param fullMsg 全提交信息
     * @param authorName 提交作者的名称
     * @param authorEmail 提交作者的邮箱
     * @param commitTime 提交时间
     * @param fullCommit 全提交hash
     */
    public CommitRecord(String shortMsg, String fullMsg, String authorName, String authorEmail,
            String commitTime, String fullCommit) {
        this.shortMsg = new SimpleStringProperty(shortMsg);
        this.fullMsg = new SimpleStringProperty(fullMsg);
        this.authorName = new SimpleStringProperty(authorName);
        this.authorEmail = new SimpleStringProperty(authorEmail);
        this.commitTime = new SimpleStringProperty(commitTime);
        this.shortCommit = new SimpleStringProperty(fullCommit.substring(0, 7));
        this.fullCommit = new SimpleStringProperty(fullCommit);
    }

    /* getter 和 setter 方法 */
    public String getShortMsg() {
        return shortMsg.get();
    }

    public StringProperty shortMsgProperty() {
        return shortMsg;
    }

    public void setShortMsg(String shortMsg) {
        this.shortMsg.set(shortMsg);
    }

    public String getFullMsg() {
        return fullMsg.get();
    }

    public StringProperty fullMsgProperty() {
        return fullMsg;
    }

    public void setFullMsg(String fullMsg) {
        this.fullMsg.set(fullMsg);
    }

    public String getAuthorName() {
        return authorName.get();
    }

    public StringProperty authorNameProperty() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName.set(authorName);
    }

    public String getAuthorEmail() {
        return authorEmail.get();
    }

    public StringProperty authorEmailProperty() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail.set(authorEmail);
    }

    public String getCommitTime() {
        return commitTime.get();
    }

    public StringProperty commitTimeProperty() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime.set(commitTime);
    }

    public String getShortCommit() {
        return shortCommit.get();
    }

    public StringProperty shortCommitProperty() {
        return shortCommit;
    }

    public void setShortCommit(String shortCommit) {
        this.shortCommit.set(shortCommit);
    }

    public String getFullCommit() {
        return fullCommit.get();
    }

    public StringProperty fullCommitProperty() {
        return fullCommit;
    }

    public void setFullCommit(String fullCommit) {
        this.fullCommit.set(fullCommit);
    }

}