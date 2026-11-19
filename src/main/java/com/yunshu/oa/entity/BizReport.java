package com.yunshu.oa.entity;

import java.util.Date;

/**
 * 节点周报
 */
public class BizReport {

    private Long id;
    private Long uid;
    private String week;
    private String content;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUid() { return uid; }
    public void setUid(Long uid) { this.uid = uid; }
    public String getWeek() { return week; }
    public void setWeek(String week) { this.week = week; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
