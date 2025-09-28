package com.ruoyi.web.controller.bizcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.bizcase.domain.BizCasePrompt;
import com.jayway.jsonpath.JsonPath;
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
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BizCasePromptControllerTest {
    private static final Logger log = LoggerFactory.getLogger(BizCasePromptControllerTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
        String loginJson = "{\"username\":\"admin\",\"password\":\"admin123\"}";
        MvcResult loginResult = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andReturn();
        String loginResponse = loginResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("登录返回: {}", loginResponse);
        token = JsonPath.read(loginResponse, "$.token");
    }

    @Test
    void testAddPromptsForCaseId3() throws Exception {
        String[] contents = {"术前", "术中", "术后"};
        int[] stepIds = {1, 2, 3};
        String[] keys = {"PRE", "INTRA", "POST"};
        for (int i = 0; i < contents.length; i++) {
            BizCasePrompt prompt = new BizCasePrompt();
            prompt.setCaseId(3L);
            prompt.setPromptType(1L); // 1：内容
            prompt.setStepId((long) stepIds[i]);
            prompt.setPromptKey(keys[i]);
            MvcResult result = mockMvc.perform(post("/system/prompt")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(prompt)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andReturn();
            String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
            log.info("增加Prompt返回: {}", response);
        }
    }

    /**
     * 测试新增 prompt_percentage 字段的功能
     */
    @Test
    @Transactional
    void testAddPromptWithPercentage() throws Exception {
        BizCasePrompt prompt = new BizCasePrompt();
        prompt.setCaseId(100L);
        prompt.setPromptType(1L); // 1：内容
        prompt.setStepId(1L);
        prompt.setPromptKey("TEST_PERCENTAGE");
        prompt.setPromptKeyScore("sk-test-score-key");
        // 设置新增的百分比字段
        prompt.setPromptPercentage(new java.math.BigDecimal("30.50"));
        
        MvcResult result = mockMvc.perform(post("/system/prompt")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prompt)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("新增带百分比字段的Prompt返回: {}", response);
        
        // 验证是否创建成功
        if (response.contains("\"code\":200")) {
            log.info("✅ 新增带prompt_percentage字段的记录成功");
        } else {
            log.error("❌ 新增带prompt_percentage字段的记录失败");
        }
    }
}

