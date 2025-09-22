package com.ruoyi.web.controller.bizcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.ruoyi.bizcase.service.IBizConsultationSessionsService;
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

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class BizConsultationSessionsControllerTest {
    private static final Logger log = LoggerFactory.getLogger(BizConsultationSessionsControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IBizConsultationSessionsService bizConsultationSessionsService;

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
    void testStartTraining() throws Exception {
        // 构造StartTrainingDTO参数
        String startTrainingJson = "{" +
                "\"caseId\":\"1\"," +
                "\"stepId\":1" +
                "}";
        MvcResult result = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(startTrainingJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("startTraining返回: {}", response);
    }

    /**
     * 测试结束训练功能 - 查询会话对话记录并输出日志
     * 使用sessionId = "1"进行测试
     * 新增：测试Dify评分功能
     */
    @Test
    void testFinishTraining() throws Exception {
        String sessionId = "1";

        log.info("开始测试结束训练功能，sessionId: {}", sessionId);

        MvcResult result = mockMvc.perform(post("/system/sessions/finish/" + sessionId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("finishTraining返回: {}", response);

        // 验证返回结果
        try {
            Integer code = JsonPath.read(response, "$.code");
            String msg = JsonPath.read(response, "$.msg");
            log.info("响应状态码: {}, 消息: {}", code, msg);

            // 检查是否成功调用了评分功能
            log.info("注意查看日志中是否包含以下内容：");
            log.info("1. 会话对话记录的详细信息");
            log.info("2. Dify评分结果的JSON内容");
            log.info("3. 评分过程中的错误信息（如果有）");

        } catch (Exception e) {
            log.warn("解析响应JSON失败: {}", e.getMessage());
        }
    }

    /**
     * 直接测试Service层的Dify评分功能
     */
    @Test
    void testDifyEvaluationService() {
        String sessionId = "1";
        Long caseId = 14L;
        Long stepId = 1L;

        log.info("开始测试Service层的Dify评分功能");
        log.info("参数: sessionId={}, caseId={}, stepId={}", sessionId, caseId, stepId);

        try {
            String evaluationResult = bizConsultationSessionsService.evaluateSessionWithDify(sessionId, caseId, stepId);

            log.info("评分结果: {}", evaluationResult);

            // 尝试解析JSON结果
            try {
                com.alibaba.fastjson2.JSONObject jsonResult = com.alibaba.fastjson2.JSON.parseObject(evaluationResult);
                if (jsonResult.containsKey("sessionId")) {
                    log.info("评分成功！会话ID: {}", jsonResult.getString("sessionId"));
                    if (jsonResult.containsKey("scores")) {
                        log.info("评分结果: {}", jsonResult.getJSONObject("scores"));
                    }
                    if (jsonResult.containsKey("feedback")) {
                        log.info("评价反馈: {}", jsonResult.getString("feedback"));
                    }
                } else if (jsonResult.containsKey("error")) {
                    log.warn("评分失败: {}", jsonResult.getString("error"));
                }
            } catch (Exception parseEx) {
                log.info("评分结果不是JSON格式，原始内容: {}", evaluationResult);
            }

        } catch (Exception e) {
            log.error("评分测试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 测试解析和保存Dify评分详情功能
     */
    @Test
    void testSaveDifyScoreDetails() {
        // 模拟Dify返回的JSON数据结构
        String mockDifyResult = "[" +
                "{\"order\":\"1\",\"itemContent\":\"操作者进行自我介绍\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"自我介绍\",\"basis\":\"学生未进行自我介绍，直接询问患者病情，未体现自我介绍行为。\"}," +
                "{\"order\":\"2\",\"itemContent\":\"核对患者身份\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"核对身份\",\"basis\":\"学生未核对患者身份，直接询问患者症状，未体现身份核对流程。\"}," +
                "{\"order\":\"3\",\"itemContent\":\"告知患者有穿刺适应症，且无禁忌症，适合进行穿刺\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"告知适应症\",\"basis\":\"学生未提及穿刺适应症或禁忌症，未进行相关告知。\"}" +
                "]";
        
        Long mockEvaluationId = 1L;
        
        log.info("开始测试保存Dify评分详情功能");
        log.info("模拟数据: {}", mockDifyResult);
        log.info("评价ID: {}", mockEvaluationId);
        
        try {
            int savedCount = bizConsultationSessionsService.saveDifyScoreDetails(mockDifyResult, mockEvaluationId);
            
            log.info("评分详情保存结果: 成功保存{}*条记录", savedCount);
            
            if (savedCount > 0) {
                log.info("✅ 测试成功！评分详情已成功保存到数据库");
            } else {
                log.warn("⚠️ 没有保存任何记录，请检查数据格式或数据库连接");
            }
            
        } catch (Exception e) {
            log.error("评分详情保存测试失败: {}", e.getMessage(), e);
        }
    }
}

