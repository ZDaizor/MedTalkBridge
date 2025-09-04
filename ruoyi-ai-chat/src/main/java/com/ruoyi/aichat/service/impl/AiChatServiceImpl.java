package com.ruoyi.aichat.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.aichat.config.AiChatProperties;
import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import com.ruoyi.aichat.dto.ScenarioType;
import com.ruoyi.aichat.service.IAiChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

/**
 * AI对话服务实现类
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Service
public class AiChatServiceImpl implements IAiChatService {

    private static final Logger logger = LoggerFactory.getLogger(AiChatServiceImpl.class);

    private final WebClient webClient;
    private final AiChatProperties aiChatProperties;

    public AiChatServiceImpl(@Qualifier("aiChatWebClient") WebClient webClient, 
                           AiChatProperties aiChatProperties) {
        this.webClient = webClient;
        this.aiChatProperties = aiChatProperties;
    }

    @Override
    public AiChatResponse chat(AiChatRequest request) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 检查服务是否启用
            if (!aiChatProperties.getEnabled()) {
                return AiChatResponse.error("AI对话服务未启用");
            }

            // 验证场景类型
            if (!ScenarioType.isValid(request.getScenarioType())) {
                return AiChatResponse.error("无效的场景类型: " + request.getScenarioType());
            }

            // 构建请求体    .

            JSONObject requestBody = buildRequestBody(request);
            
            // 调用AI API
            String responseBody = callAiApi(requestBody);
            
            // 解析响应
            AiChatResponse response = parseResponse(responseBody, request);
            
            // 设置处理时间
            response.setProcessingTime(System.currentTimeMillis() - startTime);
            
            return response;
            
        } catch (Exception e) {
            logger.error("AI对话服务调用失败", e);
            AiChatResponse errorResponse = AiChatResponse.error("AI对话服务调用失败: " + e.getMessage());
            errorResponse.setProcessingTime(System.currentTimeMillis() - startTime);
            return errorResponse;
        }
    }

    @Override
    public String getDefaultSystemPrompt(String scenarioType) {
        if (!ScenarioType.isValid(scenarioType)) {
            return "";
        }
        
        ScenarioType type = ScenarioType.fromCode(scenarioType);
        switch (type) {
            case PRE_OPERATION:
                return aiChatProperties.getPrompts().getPreOperation();
            case DURING_OPERATION:
                return aiChatProperties.getPrompts().getDuringOperation();
            case POST_OPERATION:
                return aiChatProperties.getPrompts().getPostOperation();
            default:
                return "";
        }
    }

    @Override
    public boolean isServiceAvailable() {
        try {
            // 简单的健康检查
            return aiChatProperties.getEnabled() && 
                   StringUtils.hasText(aiChatProperties.getApiKey()) &&
                   StringUtils.hasText(aiChatProperties.getBaseUrl());
        } catch (Exception e) {
            logger.error("检查AI服务可用性失败", e);
            return false;
        }
    }

    /**
     * 构建AI API请求体
     */
    private JSONObject buildRequestBody(AiChatRequest request) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", aiChatProperties.getModel());
        requestBody.put("max_tokens", request.getMaxTokens() != null ? 
                       request.getMaxTokens() : aiChatProperties.getMaxTokens());
        requestBody.put("temperature", request.getTemperature() != null ? 
                       request.getTemperature() : aiChatProperties.getTemperature());

        // 构建消息数组
        JSONArray messages = new JSONArray();
        
        // 系统消息
        String systemPrompt = StringUtils.hasText(request.getSystemPrompt()) ? 
                             request.getSystemPrompt() : getDefaultSystemPrompt(request.getScenarioType());
        if (StringUtils.hasText(systemPrompt)) {
            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);
        }
        
        // 用户消息
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", request.getUserMessage());
        messages.add(userMessage);
        
        requestBody.put("messages", messages);
        
        return requestBody;
    }

    /**
     * 调用AI API
     */
    private String callAiApi(JSONObject requestBody) {
        return webClient.post()
                .uri("/chat/completions")
                .bodyValue(requestBody.toJSONString())
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(aiChatProperties.getTimeout()))
                .block();
    }

    /**
     * 解析AI API响应
     */
    private AiChatResponse parseResponse(String responseBody, AiChatRequest request) {
        JSONObject jsonResponse = JSON.parseObject(responseBody);
        
        // 检查是否有错误
        if (jsonResponse.containsKey("error")) {
            JSONObject error = jsonResponse.getJSONObject("error");
            throw new RuntimeException("AI API错误: " + error.getString("message"));
        }
        
        // 提取AI回复内容
        JSONArray choices = jsonResponse.getJSONArray("choices");
        if (choices == null || choices.isEmpty()) {
            throw new RuntimeException("AI API响应格式错误: 没有choices");
        }
        
        JSONObject firstChoice = choices.getJSONObject(0);
        JSONObject message = firstChoice.getJSONObject("message");
        String aiMessage = message.getString("content");
        
        // 提取token使用量
        Integer tokensUsed = null;
        if (jsonResponse.containsKey("usage")) {
            JSONObject usage = jsonResponse.getJSONObject("usage");
            tokensUsed = usage.getInteger("total_tokens");
        }
        
        // 构建响应
        String sessionId = StringUtils.hasText(request.getSessionId()) ? 
                          request.getSessionId() : UUID.randomUUID().toString();
        
        AiChatResponse response = AiChatResponse.success(aiMessage, sessionId, request.getScenarioType());
        response.setTokensUsed(tokensUsed);
        
        return response;
    }
}
