package com.yunshu.oa.entity;

import java.util.Date;

/**
 * 公告
 */
public class BizNotice {

    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private Integer top;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
    public Integer getTop() { return top; }
    public void setTop(Integer top) { this.top = top; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
