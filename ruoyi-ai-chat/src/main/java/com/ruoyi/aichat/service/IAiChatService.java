package com.ruoyi.aichat.service;

import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;

/**
 * AI对话服务接口
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
public interface IAiChatService {

    /**
     * 进行AI对话
     * 
     * @param request 对话请求
     * @return 对话响应
     */
    AiChatResponse chat(AiChatRequest request);

    /**
     * 获取默认系统提示词
     * 
     * @param scenarioType 场景类型
     * @return 系统提示词
     */
    String getDefaultSystemPrompt(String scenarioType);

    /**
     * 检查AI服务是否可用
     * 
     * @return 是否可用
     */
    boolean isServiceAvailable();
}
