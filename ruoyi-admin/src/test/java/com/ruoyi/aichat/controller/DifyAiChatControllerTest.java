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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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

        MvcResult mvcResult = mockMvc.perform(post("/dify/chat")
                        .param("query", query)
                        .param("user", user)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer " + token))
                .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);
    }

    @Test
    public void testChat_continuousConversation() throws Exception {
        String query = "How are you?";
        String conversationId = "conv-123";
        String user = "test-user";
        String expectedResponse = "{\"answer\": \"I am fine, thank you!\"}";

        mockMvc.perform(post("/dify/chat")
                        .param("query", query)
                        .param("conversationId", conversationId)
                        .param("user", user)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
