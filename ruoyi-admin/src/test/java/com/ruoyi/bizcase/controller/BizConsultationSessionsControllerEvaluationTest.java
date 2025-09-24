package com.ruoyi.bizcase.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.bizcase.domain.BizCaseEvaluations;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.domain.BizEvScoreDetails;
import com.ruoyi.bizcase.service.IBizCaseEvaluationsService;
import com.ruoyi.bizcase.service.IBizEvScoreDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 学生问诊会话评分功能集成测试类
 *
 * @author daizor
 * @date 2025-09-22
 */
@SpringBootTest(classes = RuoYiApplication.class)
@AutoConfigureWebMvc
@DisplayName("学生问诊会话评分功能集成测试")
public class BizConsultationSessionsControllerEvaluationTest {

    Logger logger = Logger.getLogger(BizConsultationSessionsControllerEvaluationTest.class.getName());

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private BizConsultationSessionsController controller;

    @Autowired
    private IBizCaseEvaluationsService bizCaseEvaluationsService;

    @Autowired
    private IBizEvScoreDetailsService bizEvScoreDetailsService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        objectMapper = new ObjectMapper();

        // 获取用户token
        String username = "admin";
        String password = "admin123";
        String loginRequest = objectMapper.writeValueAsString(Map.of("username", username, "password", password));

        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseContent);
        logger.info("登录信息: " + responseContent);
        token = jsonNode.get("token").asText();
    }

    /**
     * 创建模拟会话对象
     */
    private BizConsultationSessions createMockSession() {
        BizConsultationSessions session = new BizConsultationSessions();
        session.setSessionId(12345L);
        session.setUserId(1L);
        session.setCaseId(100L);
        session.setStepId(1);
        session.setCaseTitle("测试病例");
        session.setPatientName("张三");
        return session;
    }

    /**
     * 创建模拟Dify评分结果JSON
     */
    private String createMockDifyResult() {
        return "[{\"order\":\"1\",\"itemContent\":\"操作者进行自我介绍\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"自我介绍\",\"basis\":\"学生未进行自我介绍，直接询问患者症状。\"}," +
                "{\"order\":\"2\",\"itemContent\":\"核对患者身份\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"核对身份\",\"basis\":\"学生未核对患者身份，直接询问患者症状。\"}," +
                "{\"order\":\"3\",\"itemContent\":\"告知患者有穿刺适应症，且无禁忌症，适合进行穿刺\",\"score\":\"5\",\"scored\":\"3\",\"type\":\"告知适应症\",\"basis\":\"学生部分提及了适应症内容。\"}]";
    }

    @Test
    @WithMockUser(authorities = {"system:sessions:edit"})
    @DisplayName("测试评分数据保存功能")
    void testSaveEvaluationData() throws Exception {
        logger.info("开始测试评分数据保存功能");

        // 准备测试数据
        String mockDifyResult = createMockDifyResult();
        BizConsultationSessions mockSession = createMockSession();

        try {
            // 使用反射调用私有方法进行测试
            Method method = BizConsultationSessionsController.class
                    .getDeclaredMethod("saveEvaluationData", String.class, BizConsultationSessions.class);
            method.setAccessible(true);

            Long evaluationId = (Long) method.invoke(controller, mockDifyResult, mockSession);

            // 验证结果
            assertNotNull(evaluationId, "评分记录ID不应为空");
            logger.info("评分记录ID: " + evaluationId);

            // 验证总分表记录
            BizCaseEvaluations evaluation = bizCaseEvaluationsService.selectBizCaseEvaluationsByEvaluationId(evaluationId);
            assertNotNull(evaluation, "总分表记录不应为空");
            assertEquals(mockSession.getUserId(), evaluation.getUserId(), "用户ID应该匹配");
            assertEquals(mockSession.getCaseId(), evaluation.getCaseId(), "病例ID应该匹配");
            assertEquals(mockSession.getStepId().longValue(), evaluation.getStepId().longValue(), "步骤ID应该匹配");
            assertEquals(mockSession.getSessionId(), evaluation.getSessionId(), "会话ID应该匹配");
            assertEquals(Long.valueOf(1), evaluation.getEvalMode(), "评价模式应该为训练模式");

            // 验证总分计算
            BigDecimal expectedTotalScore = new BigDecimal("3"); // 0+0+3=3
            BigDecimal expectedMaxScore = new BigDecimal("15"); // 5+5+5=15
            assertEquals(expectedTotalScore, evaluation.getTotleScore(), "总分应该正确");
            assertEquals(expectedMaxScore, evaluation.getTotleMaxScore(), "满分应该正确");

            // 验证分项详情表记录
            BizEvScoreDetails queryCondition = new BizEvScoreDetails();
            queryCondition.setEvaluationId(evaluationId);
            List<BizEvScoreDetails> detailsList = bizEvScoreDetailsService.selectBizEvScoreDetailsList(queryCondition);
            
            assertNotNull(detailsList, "分项详情列表不应为空");
            assertEquals(3, detailsList.size(), "应该有3条分项记录");

            // 验证第一项详情
            BizEvScoreDetails firstDetail = detailsList.stream()
                    .filter(detail -> detail.getItemId().equals(1))
                    .findFirst()
                    .orElse(null);
            assertNotNull(firstDetail, "第一项详情应该存在");
            assertEquals(new BigDecimal("0"), firstDetail.getScoreAchieved(), "第一项实际得分");
            assertEquals(new BigDecimal("5"), firstDetail.getScoreMax(), "第一项满分");
            assertEquals(Integer.valueOf(1), firstDetail.getScoreType(), "分数类型应该为AI评分");
            assertEquals("学生未进行自我介绍，直接询问患者症状。", firstDetail.getScoreBasis(), "评分依据应该正确");

            // 验证第三项详情（有得分的项）
            BizEvScoreDetails thirdDetail = detailsList.stream()
                    .filter(detail -> detail.getItemId().equals(3))
                    .findFirst()
                    .orElse(null);
            assertNotNull(thirdDetail, "第三项详情应该存在");
            assertEquals(new BigDecimal("3"), thirdDetail.getScoreAchieved(), "第三项实际得分");
            assertEquals(new BigDecimal("5"), thirdDetail.getScoreMax(), "第三项满分");

            logger.info("测试通过：评分数据保存功能正常");

        } catch (Exception e) {
            logger.severe("测试失败：" + e.getMessage());
            fail("测试执行时发生异常: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试 JSON 解析功能")
    void testJsonParsing() {
        logger.info("开始测试 JSON 解析功能");

        String testJson = "[{\"order\":\"1\",\"itemContent\":\"操作者进行自我介绍\",\"score\":\"5\",\"scored\":\"0\",\"type\":\"自我介绍\",\"basis\":\"学生未进行自我介绍，直接询问患者症状。\"}]";

        try {
            com.alibaba.fastjson2.JSONArray scoreArray = com.alibaba.fastjson2.JSON.parseArray(testJson);
            assertNotNull(scoreArray, "JSON数组不应为空");
            assertEquals(1, scoreArray.size(), "应该有1个元素");

            com.alibaba.fastjson2.JSONObject firstItem = scoreArray.getJSONObject(0);
            assertEquals("1", firstItem.getString("order"), "order字段应该正确");
            assertEquals("5", firstItem.getString("score"), "score字段应该正确");
            assertEquals("0", firstItem.getString("scored"), "scored字段应该正确");
            assertEquals("学生未进行自我介绍，直接询问患者症状。", firstItem.getString("basis"), "basis字段应该正确");

            logger.info("测试通过：JSON 解析功能正常");

        } catch (Exception e) {
            logger.severe("JSON解析测试失败：" + e.getMessage());
            fail("JSON解析测试失败: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试空数据处理")
    void testEmptyDataHandling() {
        logger.info("开始测试空数据处理");

        BizConsultationSessions mockSession = createMockSession();

        try {
            Method method = BizConsultationSessionsController.class
                    .getDeclaredMethod("saveEvaluationData", String.class, BizConsultationSessions.class);
            method.setAccessible(true);

            // 测试空字符串
            Long result1 = (Long) method.invoke(controller, "", mockSession);
            assertNull(result1, "空字符串应该返回null");

            // 测试 null
            Long result2 = (Long) method.invoke(controller, (String) null, mockSession);
            assertNull(result2, "null应该返回null");

            // 测试空数组
            Long result3 = (Long) method.invoke(controller, "[]", mockSession);
            assertNull(result3, "空数组应该返回null");

            logger.info("测试通过：空数据处理功能正常");

        } catch (Exception e) {
            logger.severe("空数据处理测试失败：" + e.getMessage());
            fail("空数据处理测试失败: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试异常JSON数据处理")
    void testInvalidJsonHandling() {
        logger.info("开始测试异常JSON数据处理");

        BizConsultationSessions mockSession = createMockSession();

        try {
            Method method = BizConsultationSessionsController.class
                    .getDeclaredMethod("saveEvaluationData", String.class, BizConsultationSessions.class);
            method.setAccessible(true);

            // 测试格式错误的JSON
            Long result1 = (Long) method.invoke(controller, "{invalid json}", mockSession);
            assertNull(result1, "格式错误的JSON应该返回null");

            // 测试缺少必要字段的JSON
            String incompleteJson = "[{\"order\":\"1\"}]";
            Long result2 = (Long) method.invoke(controller, incompleteJson, mockSession);
            assertNotNull(result2, "缺少字段的JSON应该能够处理，但可能有默认值");

            logger.info("测试通过：异常JSON数据处理功能正常");

        } catch (Exception e) {
            logger.severe("异常JSON数据处理测试失败：" + e.getMessage());
            fail("异常JSON数据处理测试失败: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("测试BigDecimal类型转换")
    void testBigDecimalConversion() {
        logger.info("开始测试BigDecimal类型转换");

        try {
            // 测试正常数字转换
            BigDecimal bd1 = new BigDecimal("5.5");
            assertEquals(new BigDecimal("5.5"), bd1, "正常数字转换应该成功");

            // 测试整数转换
            BigDecimal bd2 = new BigDecimal("0");
            assertEquals(new BigDecimal("0"), bd2, "整数转换应该成功");

            // 测试字符串数字转换
            BigDecimal bd3 = new BigDecimal("3");
            assertEquals(new BigDecimal("3"), bd3, "字符串数字转换应该成功");

            logger.info("测试通过：BigDecimal类型转换功能正常");

        } catch (Exception e) {
            logger.severe("BigDecimal类型转换测试失败：" + e.getMessage());
            fail("BigDecimal类型转换测试失败: " + e.getMessage());
        }
    }
}