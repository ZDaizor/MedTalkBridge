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
    private String apiKey;

    /**
     * API基础URL
     */
    private String baseUrl = "https://api.openai.com/v1";

    /**
     * 使用的模型名称
     */
    private String model = "gpt-3.5-turbo";

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

    /**
     * 默认系统提示词配置
     */
    private PromptConfig prompts = new PromptConfig();

    public static class PromptConfig {
        /**
         * 术前场景默认提示词
         */
        private String preOperation = "你是一位经验丰富的医生，正在与即将进行腰穿手术的患者进行术前沟通。请以专业、温和、耐心的态度回答患者的问题，帮助缓解患者的紧张情绪，并提供准确的医疗信息。";

        /**
         * 术中场景默认提示词
         */
        private String duringOperation = "你是一位正在进行腰穿手术的医生，需要与患者保持沟通，指导患者配合手术，安抚患者情绪。请保持专业、冷静、鼓励的语调。";

        /**
         * 术后场景默认提示词
         */
        private String postOperation = "你是一位医生，正在为刚完成腰穿手术的患者提供术后指导和关怀。请提供专业的术后注意事项，关心患者的感受，并回答相关问题。";

        public String getPreOperation() {
            return preOperation;
        }

        public void setPreOperation(String preOperation) {
            this.preOperation = preOperation;
        }

        public String getDuringOperation() {
            return duringOperation;
        }

        public void setDuringOperation(String duringOperation) {
            this.duringOperation = duringOperation;
        }

        public String getPostOperation() {
            return postOperation;
        }

        public void setPostOperation(String postOperation) {
            this.postOperation = postOperation;
        }
    }

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

    public PromptConfig getPrompts() {
        return prompts;
    }

    public void setPrompts(PromptConfig prompts) {
        this.prompts = prompts;
    }
}
