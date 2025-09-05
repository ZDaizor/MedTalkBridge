package com.ruoyi.aichat.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.aichat.config.AiChatProperties;
import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import com.ruoyi.aichat.manager.ChatHistoryManager;
import com.ruoyi.aichat.service.IAiChatService;
import com.ruoyi.common.annotation.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
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
    private final ChatHistoryManager chatHistoryManager; // 新增

    public AiChatServiceImpl(@Qualifier("aiChatWebClient") WebClient webClient,
                             AiChatProperties aiChatProperties,
                             ChatHistoryManager chatHistoryManager) { // 注入
        this.webClient = webClient;
        this.aiChatProperties = aiChatProperties;
        this.chatHistoryManager = chatHistoryManager;
    }

    @Override
    public AiChatResponse chat(AiChatRequest request) {
        long startTime = System.currentTimeMillis();

        try {
            // 检查服务是否启用
            if (!aiChatProperties.getEnabled()) {
                return AiChatResponse.error("AI对话服务未启用");
            }

            // 确保有sessionId
            String sessionId = StringUtils.hasText(request.getSessionId()) ?
                    request.getSessionId() : UUID.randomUUID().toString();
            request.setSessionId(sessionId);

            // 构建请求体（包含历史记录）
            JSONObject requestBody = buildRequestBodyWithHistory(request);

            // 调用AI API
            String responseBody = callAiApi(requestBody);

            // 解析响应
            AiChatResponse response = parseResponse(responseBody, request);

            // 保存对话历史
            if (response.getSuccess()) {
                // 保存用户消息
                chatHistoryManager.addMessage(sessionId, "user", request.getUserMessage());
                // 保存AI回复
                chatHistoryManager.addMessage(sessionId, "assistant", response.getAiMessage());
            }

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

    /**
     * 构建包含历史记录的请求体
     */
    private JSONObject buildRequestBodyWithHistory(AiChatRequest request) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", aiChatProperties.getModel());
        requestBody.put("max_tokens", request.getMaxTokens() != null ?
                request.getMaxTokens() : aiChatProperties.getMaxTokens());
        requestBody.put("temperature", request.getTemperature() != null ?
                request.getTemperature() : aiChatProperties.getTemperature());

        // 构建消息数组
        JSONArray messages = new JSONArray();

        // 1. 系统消息
        String systemPrompt = StringUtils.hasText(request.getSystemPrompt()) ?
                request.getSystemPrompt() : "";
        if (StringUtils.hasText(systemPrompt)) {
            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", systemPrompt);
            messages.add(systemMessage);
        }

        // 2. 添加历史对话
        List<ChatHistoryManager.ChatMessage> history = chatHistoryManager.getHistory(request.getSessionId());
        for (ChatHistoryManager.ChatMessage msg : history) {
            JSONObject historyMessage = new JSONObject();
            historyMessage.put("role", msg.getRole());
            historyMessage.put("content", msg.getContent());
            messages.add(historyMessage);
        }

        // 3. 当前用户消息
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", request.getUserMessage());
        messages.add(userMessage);

        requestBody.put("messages", messages);

        logger.info("对话历史数量: {}, SessionId: {}", history.size(), request.getSessionId());

        return requestBody;
    }

    // 新增：清除会话历史的方法
    public void clearChatHistory(String sessionId) {
        chatHistoryManager.clearHistory(sessionId);
        logger.info("已清除会话历史: {}", sessionId);
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
                request.getSystemPrompt() : "";
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
        String url = "https://api.5202030.xyz/v1/chat/completions";
        String requestBodyStr = requestBody.toJSONString();

        // 打印请求信息
        logger.info("========== AI API 请求信息 ==========");
        logger.info("请求URL: {}", url);
        logger.info("请求Body: {}", requestBodyStr);
        logger.info("超时时间: {}秒", aiChatProperties.getTimeout());

        try {
            String response = webClient.post()
                    .uri(url)
                    .bodyValue(requestBodyStr)
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(aiChatProperties.getTimeout()))
                    .block();

            // 打印响应信息
            logger.info("========== AI API 响应信息 ==========");
            logger.info("响应内容: {}", response);

            return response;
        } catch (Exception e) {
            logger.error("调用AI API失败", e);
            throw e;
        }
    }

    /**
     * 解析AI API响应（兼容多种提供商/网关的返回结构）
     */
    private AiChatResponse parseResponse(String responseBody, AiChatRequest request) {
        JSONObject json = JSON.parseObject(responseBody);

        // 1) 标准错误结构
        if (json.containsKey("error")) {
            JSONObject error = json.getJSONObject("error");
            throw new RuntimeException("AI API错误: " + error.getString("message"));
        }

        // 2) 提取文本内容（多种兼容分支）
        String aiMessage = null;

        // 2.1 OpenAI Chat Completions 风格
        JSONArray choices = json.getJSONArray("choices");
        if (choices != null && !choices.isEmpty()) {
            JSONObject firstChoice = choices.getJSONObject(0);

            // 优先 message.content
            JSONObject messageObj = firstChoice.getJSONObject("message");
            if (messageObj != null) {
                Object contentObj = messageObj.get("content");
                aiMessage = extractTextFromContent(contentObj);
            }

            // 退化到 choice.text（有些网关做了兼容）
            if (!StringUtils.hasText(aiMessage)) {
                String text = firstChoice.getString("text");
                if (StringUtils.hasText(text)) {
                    aiMessage = text;
                }
            }
        }

        // 2.2 Gemini 风格（candidates[0].content.parts[].text）
        if (!StringUtils.hasText(aiMessage)) {
            JSONArray candidates = json.getJSONArray("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                JSONObject c0 = candidates.getJSONObject(0);
                JSONObject content = c0.getJSONObject("content");
                if (content != null) {
                    Object parts = content.get("parts");
                    aiMessage = extractTextFromParts(parts);
                }
            }
        }

        // 2.3 Anthropic 风格（content 为数组，元素含 text）
        if (!StringUtils.hasText(aiMessage)) {
            Object contentRoot = json.get("content");
            aiMessage = extractTextFromContent(contentRoot);
        }

        if (!StringUtils.hasText(aiMessage)) {
            // 保底：记录部分响应方便定位问题
            String brief = responseBody;
            if (brief != null && brief.length() > 500) {
                brief = brief.substring(0, 500) + "...";
            }
            logger.warn("未能从AI响应中解析到文本，响应摘要: {}", brief);
            throw new RuntimeException("AI API响应不包含可用的文本内容");
        }

        // 3) 提取token使用量（多种结构兼容）
        Integer tokensUsed = null;
        if (json.containsKey("usage")) {
            JSONObject usage = json.getJSONObject("usage");
            tokensUsed = usage.getInteger("total_tokens");
            if (tokensUsed == null) {
                Integer prompt = usage.getInteger("prompt_tokens");
                Integer completion = usage.getInteger("completion_tokens");
                if (prompt != null && completion != null) {
                    tokensUsed = prompt + completion;
                }
            }
        } else if (json.containsKey("usageMetadata")) { // Gemini
            JSONObject usage = json.getJSONObject("usageMetadata");
            tokensUsed = usage.getInteger("totalTokenCount");
        }

        // 4) 构建响应
        String sessionId = StringUtils.hasText(request.getSessionId()) ?
                request.getSessionId() : UUID.randomUUID().toString();

        AiChatResponse response = AiChatResponse.success(aiMessage, sessionId);
        response.setTokensUsed(tokensUsed);
        return response;
    }

    // 提取 content 文本（兼容 String / JSONArray / JSONObject）
    private String extractTextFromContent(Object contentObj) {
        if (contentObj == null) return null;
        if (contentObj instanceof String) {
            return ((String) contentObj).trim();
        }
        if (contentObj instanceof JSONArray) {
            StringBuilder sb = new StringBuilder();
            for (Object part : (JSONArray) contentObj) {
                if (part instanceof String) {
                    sb.append((String) part);
                } else if (part instanceof JSONObject) {
                    JSONObject p = (JSONObject) part;
                    // OpenAI function/tool 调用之外的 text
                    if (StringUtils.hasText(p.getString("text"))) {
                        sb.append(p.getString("text"));
                    } else if (StringUtils.hasText(p.getString("content"))) {
                        sb.append(p.getString("content"));
                    }
                }
            }
            return sb.toString().trim();
        }
        if (contentObj instanceof JSONObject) {
            JSONObject obj = (JSONObject) contentObj;
            if (StringUtils.hasText(obj.getString("text"))) {
                return obj.getString("text").trim();
            }
            Object parts = obj.get("parts");
            String fromParts = extractTextFromParts(parts);
            if (StringUtils.hasText(fromParts)) return fromParts;
        }
        return null;
    }

    // 提取 Gemini parts 文本
    private String extractTextFromParts(Object partsObj) {
        if (partsObj == null) return null;
        if (partsObj instanceof JSONArray) {
            StringBuilder sb = new StringBuilder();
            for (Object p : (JSONArray) partsObj) {
                if (p instanceof JSONObject) {
                    String text = ((JSONObject) p).getString("text");
                    if (StringUtils.hasText(text)) sb.append(text);
                } else if (p instanceof String) {
                    sb.append((String) p);
                }
            }
            return sb.toString().trim();
        }
        if (partsObj instanceof JSONObject) {
            String text = ((JSONObject) partsObj).getString("text");
            return StringUtils.hasText(text) ? text.trim() : null;
        }
        if (partsObj instanceof String) {
            return ((String) partsObj).trim();
        }
        return null;
    }
}
