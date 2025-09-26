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
 * 病例类型分布Controller测试类
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CaseTypeDistributionControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CaseTypeDistributionControllerTest.class);
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void testGetCaseTypeDistribution() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/dashboard/case-type-distribution")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("病例类型分布返回: {}", response);
        
        // 验证返回数据结构
        if (!response.contains("\"data\":[]")) {
            // 如果有数据，验证数据结构
            mockMvc.perform(get("/system/dashboard/case-type-distribution")
                            .header("Authorization", "Bearer " + token))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data").isArray())
                    .andExpect(jsonPath("$.data[0].caseType").isString())
                    .andExpect(jsonPath("$.data[0].completedCount").isNumber())
                    .andExpect(jsonPath("$.data[0].percentage").isNumber());
        }
    }
}