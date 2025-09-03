package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问诊对话记录详情对象 biz_consultation_se_messages
 * 
 * @author daizor
 * @date 2025-09-03
 */
public class BizConsultationSeMessages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键，自增ID */
    private String messagesId;

    /** 关联问诊会话表 */
    @Excel(name = "关联问诊会话表")
    private String sessionId;

    /** 消息顺序号 */
    @Excel(name = "消息顺序号")
    private String messageOrder;

    /** 发送者类型(1:患者，2 学生) */
    @Excel(name = "发送者类型(1:患者，2 学生)")
    private Long senderType;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String messageContent;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String messageType;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timestamp;

    /** 响应时间(秒) */
    @Excel(name = "响应时间(秒)")
    private String responseTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setMessagesId(String messagesId) 
    {
        this.messagesId = messagesId;
    }

    public String getMessagesId() 
    {
        return messagesId;
    }

    public void setSessionId(String sessionId) 
    {
        this.sessionId = sessionId;
    }

    public String getSessionId() 
    {
        return sessionId;
    }

    public void setMessageOrder(String messageOrder) 
    {
        this.messageOrder = messageOrder;
    }

    public String getMessageOrder() 
    {
        return messageOrder;
    }

    public void setSenderType(Long senderType) 
    {
        this.senderType = senderType;
    }

    public Long getSenderType() 
    {
        return senderType;
    }

    public void setMessageContent(String messageContent) 
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent() 
    {
        return messageContent;
    }

    public void setMessageType(String messageType) 
    {
        this.messageType = messageType;
    }

    public String getMessageType() 
    {
        return messageType;
    }

    public void setTimestamp(Date timestamp) 
    {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() 
    {
        return timestamp;
    }

    public void setResponseTime(String responseTime) 
    {
        this.responseTime = responseTime;
    }

    public String getResponseTime() 
    {
        return responseTime;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("messagesId", getMessagesId())
            .append("sessionId", getSessionId())
            .append("messageOrder", getMessageOrder())
            .append("senderType", getSenderType())
            .append("messageContent", getMessageContent())
            .append("messageType", getMessageType())
            .append("timestamp", getTimestamp())
            .append("responseTime", getResponseTime())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}
