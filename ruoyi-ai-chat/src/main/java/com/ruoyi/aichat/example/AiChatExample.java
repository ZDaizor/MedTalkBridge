package com.ruoyi.aichat.example;

import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import com.ruoyi.aichat.service.IAiChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AI对话使用示例
 * 
 * @author ruoyi
 * @date 2025-09-03
 */
@Component
public class AiChatExample {

    @Autowired
    private IAiChatService aiChatService;

    /**
     * 术前对话示例
     */
    public void preOperationExample() {
        System.out.println("=== 术前对话示例 ===");
        
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("医生，我明天要做腰穿手术，很紧张怎么办？");
        request.setSessionId("pre_op_session_001");
        
        AiChatResponse response = aiChatService.chat(request);
        
        if (response.getSuccess()) {
            System.out.println("患者: " + request.getUserMessage());
            System.out.println("医生: " + response.getAiMessage());
            System.out.println("处理时间: " + response.getProcessingTime() + "ms");
            System.out.println("Token使用: " + response.getTokensUsed());
        } else {
            System.out.println("对话失败: " + response.getErrorMessage());
        }
    }

    /**
     * 术中对话示例
     */
    public void duringOperationExample() {
        System.out.println("\n=== 术中对话示例 ===");
        
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("医生，我感觉有点疼，这正常吗？");
        request.setSessionId("during_op_session_001");
        
        AiChatResponse response = aiChatService.chat(request);
        
        if (response.getSuccess()) {
            System.out.println("患者: " + request.getUserMessage());
            System.out.println("医生: " + response.getAiMessage());
        } else {
            System.out.println("对话失败: " + response.getErrorMessage());
        }
    }

    /**
     * 术后对话示例
     */
    public void postOperationExample() {
        System.out.println("\n=== 术后对话示例 ===");
        
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("手术结束了，我需要注意什么？");
        request.setSessionId("post_op_session_001");
        
        AiChatResponse response = aiChatService.chat(request);
        
        if (response.getSuccess()) {
            System.out.println("患者: " + request.getUserMessage());
            System.out.println("医生: " + response.getAiMessage());
        } else {
            System.out.println("对话失败: " + response.getErrorMessage());
        }
    }

    /**
     * 自定义提示词示例
     */
    public void customPromptExample() {
        System.out.println("\n=== 自定义提示词示例 ===");
        
        String customPrompt = "你是一位专门从事腰椎疾病治疗的专家医生，" +
                             "拥有20年的临床经验。请以专业、耐心、温和的语调回答患者问题，" +
                             "并提供详细的医疗建议。";
        
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("医生，腰穿手术的风险大吗？");
        request.setSystemPrompt(customPrompt);
        request.setSessionId("custom_session_001");
        request.setMaxTokens(800);
        request.setTemperature(0.6);
        
        AiChatResponse response = aiChatService.chat(request);
        
        if (response.getSuccess()) {
            System.out.println("患者: " + request.getUserMessage());
            System.out.println("专家医生: " + response.getAiMessage());
        } else {
            System.out.println("对话失败: " + response.getErrorMessage());
        }
    }

    /**
     * 服务健康检查示例
     */
    public void healthCheckExample() {
        System.out.println("\n=== 服务健康检查示例 ===");
        
        boolean isAvailable = aiChatService.isServiceAvailable();
        System.out.println("AI服务状态: " + (isAvailable ? "可用" : "不可用"));
        
        if (isAvailable) {
            System.out.println("可以正常使用AI对话功能");
        } else {
            System.out.println("AI服务暂时不可用，请检查配置");
        }
    }
}
