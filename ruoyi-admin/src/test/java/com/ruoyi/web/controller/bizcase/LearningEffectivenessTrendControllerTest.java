package com.ruoyi.web.controller.bizcase;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 学习效果趋势Controller测试类
 * 
 * @author ruoyi
 * @date 2025-09-27
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LearningEffectivenessTrendControllerTest {

    private static final Logger log = LoggerFactory.getLogger(LearningEffectivenessTrendControllerTest.class);
    
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
        String loginResponse = loginResult.getResponse().getContentAsString();
        log.info("登录返回: {}", loginResponse);
        token = JsonPath.read(loginResponse, "$.token");
    }

    @Test
    void testGetLearningEffectivenessTrend() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/dashboard/learning-effectiveness-trend")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("学习效果趋势返回: {}", response);
        
        if (!response.contains("\"data\":[]")) {
            mockMvc.perform(get("/system/dashboard/learning-effectiveness-trend")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data").isArray())
                    .andExpect(jsonPath("$.data[0].month").isString())
                    .andExpect(jsonPath("$.data[0].avgScore").isNumber());
        }
    }
}
