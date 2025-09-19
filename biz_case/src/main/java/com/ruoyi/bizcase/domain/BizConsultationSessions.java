package com.ruoyi.bizcase.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生问诊列对象 biz_consultation_sessions
 *
 * @author daizor
 * @date 2025-09-04
 */
public class BizConsultationSessions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增ID
     */
    private Long sessionId;

    /**
     * 关联用户表
     */
    @Excel(name = "关联用户表")
    private Long userId;

    /**
     * 关联病例表
     */
    @Excel(name = "关联病例表")
    private Long caseId;

    /**
     * 步骤 id
     */
    @Excel(name = "步骤 id")
    private Integer stepId;

    /**
     * 病例标题
     */
    @Excel(name = "病例标题")
    private String caseTitle;

    /**
     * 模拟患者姓名
     */
    @Excel(name = "模拟患者姓名")
    private String patientName;

    /**
     * 总问诊时长(秒)
     */
    @Excel(name = "总问诊时长(秒)")
    private String totalDuration;

    /**
     * 总消息数量
     */
    @Excel(name = "总消息数量")
    private String messageCount;

    /**
     * 问诊状态(0:作为，1：完成，2：未完成)
     */
    @Excel(name = "问诊状态(0:作为，1：完成，2：未完成)")
    private Long status;

    /**
     * 模式（1：训练，2：考试）
     */
    @Excel(name = "模式", readConverterExp = "1=训练,2=考试")
    private Integer evalMode;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setEvalMode(Integer evalMode) {
        this.evalMode = evalMode;
    }

    public Integer getEvalMode() {
        return evalMode;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sessionId", getSessionId())
                .append("userId", getUserId())
                .append("caseId", getCaseId())
                .append("stepId", getStepId())
                .append("caseTitle", getCaseTitle())
                .append("patientName", getPatientName())
                .append("totalDuration", getTotalDuration())
                .append("messageCount", getMessageCount())
                .append("status", getStatus())
                .append("evalMode", getEvalMode())
                .append("endTime", getEndTime())
                .toString();
    }
}
