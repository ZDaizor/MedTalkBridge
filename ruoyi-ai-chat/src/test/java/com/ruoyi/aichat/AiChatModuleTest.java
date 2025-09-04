package com.ruoyi.aichat;

import com.ruoyi.aichat.dto.AiChatRequest;
import com.ruoyi.aichat.dto.AiChatResponse;
import com.ruoyi.aichat.dto.ScenarioType;
import com.ruoyi.aichat.service.IAiChatService;
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
     * 测试场景类型枚举
     */
    @Test
    public void testScenarioType() {
        // 测试有效的场景类型
        assertTrue(ScenarioType.isValid("PRE_OPERATION"));
        assertTrue(ScenarioType.isValid("DURING_OPERATION"));
        assertTrue(ScenarioType.isValid("POST_OPERATION"));
        
        // 测试无效的场景类型
        assertFalse(ScenarioType.isValid("INVALID_TYPE"));
        assertFalse(ScenarioType.isValid(""));
        assertFalse(ScenarioType.isValid(null));
        
        // 测试枚举转换
        assertEquals(ScenarioType.PRE_OPERATION, ScenarioType.fromCode("PRE_OPERATION"));
        assertEquals("术前", ScenarioType.PRE_OPERATION.getDescription());
        
        // 测试异常情况
        assertThrows(IllegalArgumentException.class, () -> {
            ScenarioType.fromCode("INVALID_TYPE");
        });
    }

    /**
     * 测试请求DTO
     */
    @Test
    public void testAiChatRequest() {
        AiChatRequest request = new AiChatRequest();
        request.setUserMessage("测试消息");
        request.setScenarioType("PRE_OPERATION");
        request.setSystemPrompt("测试提示词");
        request.setSessionId("test_session");
        request.setMaxTokens(500);
        request.setTemperature(0.8);
        
        assertEquals("测试消息", request.getUserMessage());
        assertEquals("PRE_OPERATION", request.getScenarioType());
        assertEquals("测试提示词", request.getSystemPrompt());
        assertEquals("test_session", request.getSessionId());
        assertEquals(Integer.valueOf(500), request.getMaxTokens());
        assertEquals(Double.valueOf(0.8), request.getTemperature());
        
        // 测试构造函数
        AiChatRequest request2 = new AiChatRequest("消息", "POST_OPERATION");
        assertEquals("消息", request2.getUserMessage());
        assertEquals("POST_OPERATION", request2.getScenarioType());
    }

    /**
     * 测试响应DTO
     */
    @Test
    public void testAiChatResponse() {
        // 测试成功响应
        AiChatResponse successResponse = AiChatResponse.success("AI回复", "session123", "PRE_OPERATION");
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
        AiChatRequest request = new AiChatRequest("测试", "PRE_OPERATION");
        assertNotNull(request.toString());
        assertTrue(request.toString().contains("测试"));
        
        AiChatResponse response = AiChatResponse.success("回复", "session", "PRE_OPERATION");
        assertNotNull(response.toString());
        assertTrue(response.toString().contains("回复"));
    }
}
