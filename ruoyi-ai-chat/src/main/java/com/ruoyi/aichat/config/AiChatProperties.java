package com.ruoyi.aichat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI对话配置属性
 *
 * @author ruoyi
 * @date 2025-09-03
 */
@Component
@ConfigurationProperties(prefix = "ai.chat")
public class AiChatProperties {

    /**
     * AI服务提供商类型 (openai, azure, baidu, etc.)
     */
    private String provider = "openai";

    /**
     * API密钥
     */
    private String apiKey = "sk-FnpGSuXKc2kJ1H3_LrlEH4ZhBpaNXYH5vJqXOZcUJ8G2sM4ozp2jvw6aLK0";

    /**
     * API基础URL
     */
    private String baseUrl = "https://api.5202030.xyz";

    /**
     * 使用的模型名称
     */
    private String model = "gemini-2.5-flash";

    /**
     * 默认最大token数
     */
    private Integer maxTokens = 1000;

    /**
     * 默认温度参数
     */
    private Double temperature = 0.7;

    /**
     * 请求超时时间（秒）
     */
    private Integer timeout = 30;

    /**
     * 重试次数
     */
    private Integer retryCount = 3;

    /**
     * 是否启用AI对话功能
     */
    private Boolean enabled = true;

    // Getters and Setters
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
