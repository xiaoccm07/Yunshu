package com.yunshu.oa.entity;

import java.util.Date;

/**
 * 审批流程实例
 */
public class FlowInstance {

    private Long id;
    private String title;
    private String formData;
    private Long applicantId;
    private Integer status;
    private Date createTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getFormData() { return formData; }
    public void setFormData(String formData) { this.formData = formData; }
    public Long getApplicantId() { return applicantId; }
    public void setApplicantId(Long applicantId) { this.applicantId = applicantId; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
