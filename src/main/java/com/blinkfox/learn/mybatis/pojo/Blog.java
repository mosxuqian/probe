package com.blinkfox.learn.mybatis.pojo;

import java.util.Date;

/**
 * 博客的实体类.
 * @author blinkfox on 2017-09-13.
 */
public class Blog {

    private int id;

    private String title;

    private String content;

    private int visitCount;

    private Date insertTime;

    private Date lastUpdateTime;

    /**
     * 空构造方法.
     */
    public Blog() {
        super();
    }

    /* getter和setter方法. */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

}