package com.ruoyi.aichat;

import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AI对话模块测试类
 *
 * @author ruoyi
 * @date 2025-09-03
 */
@SpringBootTest
@TestPropertySource(properties = {
        "ai.chat.enabled=false"  // 测试时禁用实际的AI调用
})
public class AiChatModuleTest {

    /**
     * 测试请求DTO
     */
    @Test
    public void testAiChatRequest() {
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("测试消息");
        request.setSystemPrompt("测试提示词");
        request.setSessionId("test_session");
        request.setMaxTokens(500);
        request.setTemperature(0.8);

        assertEquals("测试消息", request.getUserMessage());
        assertEquals("测试提示词", request.getSystemPrompt());
        assertEquals("test_session", request.getSessionId());
        assertEquals(Integer.valueOf(500), request.getMaxTokens());
        assertEquals(Double.valueOf(0.8), request.getTemperature());
    }

    /**
     * 测试响应DTO
     */
    @Test
    public void testAiChatResponse() {
        // 测试成功响应
        AiChatResponse successResponse = AiChatResponse.success("AI回复", "session123");
        assertTrue(successResponse.getSuccess());
        assertEquals("AI回复", successResponse.getAiMessage());
        assertEquals("session123", successResponse.getSessionId());
        assertEquals("PRE_OPERATION", successResponse.getScenarioType());
        assertNotNull(successResponse.getTimestamp());

        // 测试错误响应
        AiChatResponse errorResponse = AiChatResponse.error("错误信息");
        assertFalse(errorResponse.getSuccess());
        assertEquals("错误信息", errorResponse.getErrorMessage());
        assertNotNull(errorResponse.getTimestamp());

        // 测试设置处理时间和token使用量
        successResponse.setProcessingTime(1500L);
        successResponse.setTokensUsed(200);
        assertEquals(Long.valueOf(1500L), successResponse.getProcessingTime());
        assertEquals(Integer.valueOf(200), successResponse.getTokensUsed());
    }

    /**
     * 测试toString方法
     */
    @Test
    public void testToStringMethods() {
        AiChatRequest request = new AiChatRequest("测试");
        assertNotNull(request.toString());
        assertTrue(request.toString().contains("测试"));

        AiChatResponse response = AiChatResponse.success("回复", "session");
        assertNotNull(response.toString());
        assertTrue(response.toString().contains("回复"));
    }
}
