package com.ruoyi.aichat.controller;

import com.jayway.jsonpath.JsonPath;
import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.web.controller.bizcase.BizCaseControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DifyAiChatControllerTest {

    private static final Logger log = LoggerFactory.getLogger(DifyAiChatControllerTest.class);

    @Autowired
    private MockMvc mockMvc;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
        // 登录获取token
        String loginJson = "{\"username\":\"admin\",\"password\":\"admin123\"}";
        MvcResult loginResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();
        String loginResponse = loginResult.getResponse().getContentAsString();
        log.info("登录返回: {}", loginResponse);
        token = JsonPath.read(loginResponse, "$.token");
    }

    @Test
    public void testChat_firstConversation() throws Exception {
        String query = "你怎么了";
        String user = "test-user";
        String caseId = "1";
        String stepId = "1";

        MvcResult mvcResult = mockMvc.perform(post("/dify/chat")
                        .param("query", query)
                        .param("user", user)
                        .param("caseId", caseId)
                        .param("stepId", stepId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer " + token))
                .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);
    }

    @Test
    public void testChat_continuousConversation() throws Exception {
        String query = "你刚才在说什么，我没听清";
        String conversationId = "b7534084-d7a6-4cf1-9557-e7fb362fd091";
        String user = "test-user";
        String caseId = "1";
        String stepId = "1";
        String expectedResponse = "{\"answer\": \"I am fine, thank you!\"}";

        mockMvc.perform(post("/dify/chat")
                        .param("query", query)
                        .param("conversationId", conversationId)
                        .param("user", user)
                        .param("caseId", caseId)
                        .param("stepId", stepId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }


    @Test
    public void testParameters_keyNotFound() throws Exception {
        mockMvc.perform(get("/dify/parameters")
                        .param("caseId", "99999")
                        .param("stepId", "88888")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound())
                .andExpect(content().string("API key not found"));
    }

    @Test
    public void testParameters_success() throws Exception {
        // 这里假设数据库有对应的 case_id 和 step_id，且能查到 key，Dify 返回 {"result":"ok"}
        MvcResult mvcResult = mockMvc.perform(get("/dify/parameters")
                        .param("caseId", "1")
                        .param("stepId", "1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);
    }

    @Test
    public void testTextToAudio_success() throws Exception {
        String text = "你是哆啦A梦";
        String user = "test-user";
        String messageId = "3980d690-658c-442f-811c-c450568e9467";
        String caseId = "1";
        String stepId = "1";

        MvcResult mvcResult = mockMvc.perform(post("/dify/text-to-audio")
                        .param("text", text)
                        .param("user", user)
                        .param("messageId", messageId)
                        .param("caseId", caseId)
                        .param("stepId", stepId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        byte[] audioBytes = mvcResult.getResponse().getContentAsByteArray();
        String contentType = mvcResult.getResponse().getContentType();
        System.out.println("Content-Type: " + contentType);
        System.out.println("Audio bytes length: " + audioBytes.length);
        // 校验音频流返回
        assert audioBytes.length > 0;
        assert contentType != null && (contentType.contains("audio") || contentType.contains("octet-stream"));
    }
}
