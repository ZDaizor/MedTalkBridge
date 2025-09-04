package com.ruoyi.aichat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * AI对话响应DTO
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@ApiModel(description = "AI对话响应")
public class AiChatResponse {

    @ApiModelProperty(value = "AI回复内容", example = "我理解您对手术的担心，这是很正常的反应。腰穿是一个相对安全的检查...")
    private String aiMessage;

    @ApiModelProperty(value = "会话ID", example = "session_123456")
    private String sessionId;

    @ApiModelProperty(value = "场景类型", example = "PRE_OPERATION")
    private String scenarioType;

    @ApiModelProperty(value = "响应时间戳")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "消耗的token数量", example = "150")
    private Integer tokensUsed;

    @ApiModelProperty(value = "处理耗时（毫秒）", example = "1200")
    private Long processingTime;

    @ApiModelProperty(value = "是否成功", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "错误信息", example = "")
    private String errorMessage;

    public AiChatResponse() {
        this.timestamp = LocalDateTime.now();
        this.success = true;
    }

    public AiChatResponse(String aiMessage, String sessionId, String scenarioType) {
        this();
        this.aiMessage = aiMessage;
        this.sessionId = sessionId;
        this.scenarioType = scenarioType;
    }

    public static AiChatResponse success(String aiMessage, String sessionId, String scenarioType) {
        return new AiChatResponse(aiMessage, sessionId, scenarioType);
    }

    public static AiChatResponse error(String errorMessage) {
        AiChatResponse response = new AiChatResponse();
        response.setSuccess(false);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public String getAiMessage() {
        return aiMessage;
    }

    public void setAiMessage(String aiMessage) {
        this.aiMessage = aiMessage;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getScenarioType() {
        return scenarioType;
    }

    public void setScenarioType(String scenarioType) {
        this.scenarioType = scenarioType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTokensUsed() {
        return tokensUsed;
    }

    public void setTokensUsed(Integer tokensUsed) {
        this.tokensUsed = tokensUsed;
    }

    public Long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Long processingTime) {
        this.processingTime = processingTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "AiChatResponse{" +
                "aiMessage='" + aiMessage + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", scenarioType='" + scenarioType + '\'' +
                ", timestamp=" + timestamp +
                ", tokensUsed=" + tokensUsed +
                ", processingTime=" + processingTime +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
