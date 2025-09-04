package com.ruoyi.bizcase.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生问诊列对象 biz_consultation_sessions
 * 
 * @author daizor
 * @date 2025-09-04
 */
public class BizConsultationSessions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增ID */
    private String sessionId;

    /** 关联用户表 */
    @Excel(name = "关联用户表")
    private String userId;

    /** 关联病例表 */
    @Excel(name = "关联病例表")
    private String caseId;

    /** 病例标题 */
    @Excel(name = "病例标题")
    private String caseTitle;

    /** 模拟患者姓名 */
    @Excel(name = "模拟患者姓名")
    private String patientName;

    /** 总问诊时长(秒) */
    @Excel(name = "总问诊时长(秒)")
    private String totalDuration;

    /** 总消息数量 */
    @Excel(name = "总消息数量")
    private String messageCount;

    /** 问诊状态(0:作为，1：完成，2：未完成) */
    @Excel(name = "问诊状态(0:作为，1：完成，2：未完成)")
    private Long status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setSessionId(String sessionId) 
    {
        this.sessionId = sessionId;
    }

    public String getSessionId() 
    {
        return sessionId;
    }

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public void setCaseId(String caseId) 
    {
        this.caseId = caseId;
    }

    public String getCaseId() 
    {
        return caseId;
    }

    public void setCaseTitle(String caseTitle) 
    {
        this.caseTitle = caseTitle;
    }

    public String getCaseTitle() 
    {
        return caseTitle;
    }

    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }

    public void setTotalDuration(String totalDuration) 
    {
        this.totalDuration = totalDuration;
    }

    public String getTotalDuration() 
    {
        return totalDuration;
    }

    public void setMessageCount(String messageCount) 
    {
        this.messageCount = messageCount;
    }

    public String getMessageCount() 
    {
        return messageCount;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }

    public void setUpdatedTime(Date updatedTime) 
    {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() 
    {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sessionId", getSessionId())
            .append("userId", getUserId())
            .append("caseId", getCaseId())
            .append("caseTitle", getCaseTitle())
            .append("patientName", getPatientName())
            .append("totalDuration", getTotalDuration())
            .append("messageCount", getMessageCount())
            .append("status", getStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .toString();
    }
}
