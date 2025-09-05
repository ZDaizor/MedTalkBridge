package com.ruoyi.aichat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * AI对话请求DTO
 *
 * @author ruoyi
 * @date 2025-09-03
 */
@ApiModel(description = "AI对话请求")
public class AiChatRequest {

    @ApiModelProperty(value = "用户消息内容", required = true, example = "医生，我明天要做腰穿手术，很紧张怎么办？")
    @NotBlank(message = "用户消息内容不能为空")
    private String userMessage;


    @ApiModelProperty(value = "系统提示词", required = false,
            example = "你是一位经验丰富的医生，正在与即将进行腰穿手术的患者进行术前沟通...")
    private String systemPrompt;

    @ApiModelProperty(value = "会话ID", required = false, example = "session_123456")
    private String sessionId;

    @ApiModelProperty(value = "最大回复长度", required = false, example = "500")
    private Integer maxTokens;

    @ApiModelProperty(value = "温度参数", required = false, example = "0.7",
            notes = "控制回复的随机性，0-1之间，值越高越随机")
    private Double temperature;

    public AiChatRequest() {
    }

    public AiChatRequest(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }


    public String getSystemPrompt() {
        return systemPrompt;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "AiChatRequest{" +
                "userMessage='" + userMessage + '\'' +
                ", systemPrompt='" + systemPrompt + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", maxTokens=" + maxTokens +
                ", temperature=" + temperature +
                '}';
    }
}
