package com.ruoyi.bizcase.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Dashboard Statistics Controller Exam Statistics Interface Test Class
 * 
 * Category IDs:
 * 1 - Exam Participants
 * 2 - Passed Exams
 * 3 - Excellent Scores (90+)
 * 4 - Need Retake
 *
 * @author ruoyi
 * @date 2025-09-26
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DashboardStatisticsControllerExamTest {

    private static final Logger log = LoggerFactory.getLogger(DashboardStatisticsControllerExamTest.class);

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
        log.info("Login Response: {}", loginResponse);
        token = JsonPath.read(loginResponse, "$.token");
    }

    @Test
    void testGetExamStatistics() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/dashboard/exam-statistics")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        
        String responseContent = result.getResponse().getContentAsString();
        log.info("Exam Statistics Response: {}", responseContent);
    }
}
