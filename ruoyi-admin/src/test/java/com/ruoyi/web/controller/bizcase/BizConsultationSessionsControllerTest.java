package com.ruoyi.web.controller.bizcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.domain.BizConsultationSessions;
import com.ruoyi.bizcase.domain.dto.StartTrainingDTO;
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
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 学生问诊会话Controller测试类
 * 
 * @author daizor
 * @date 2025-09-23
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BizConsultationSessionsControllerTest {

    private static final Logger log = LoggerFactory.getLogger(BizConsultationSessionsControllerTest.class);
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String token;
    private BizCase testCase;
    private StartTrainingDTO startTrainingDTO;
    private BizConsultationSessions testSession;

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

        // 创建胸腔穿刺病例：患者赵凤芹，69岁退休女工人
        testCase = new BizCase();
        testCase.setCaseName("胸腔穿刺术前沟通病例");
        testCase.setCaseStatus(1L);
        testCase.setPatientName("赵凤芹");
        testCase.setPatientAge(69L);
        testCase.setPatientGender(2L); // 女
        testCase.setCaseDifficulty(3); // 高级（胸腔穿刺为有创操作）
        testCase.setCaseSectionsType(203L); // 呼吸系统疾病分类
        testCase.setCaseAbstract("69岁退休女工人，因胸闷气促1月，胸部CT示右侧胸腔积液，需行胸腔穿刺术。患者对手术存在恐惧心理，需要细致的术前沟通。");
        testCase.setCaseHpi("胸闷气促1月余，活动后加重，伴咳嗽少痰，无发热、胸痛。近1周症状逐渐加重，影响日常生活。");
        testCase.setCaseHa("无药物过敏史，无食物过敏史。");
        testCase.setCasePh("既往体健，无高血压、糖尿病等慢性疾病史。10年前因胆囊结石行胆囊切除术，术后恢复良好。");
        testCase.setCaseAic("胸闷气促1月余伴咳嗽");
        testCase.setCaseHe("T:36.8℃，P:88次/分，R:22次/分，BP:135/80mmHg。一般情况可，神志清楚，精神紧张。右侧胸廓饱满，触觉语颤减弱，叩诊浊音，听诊呼吸音减弱。心率规整，无杂音。腹部平软，无压痛。");
        testCase.setCaseAe("胸部CT：右侧胸腔大量积液，纵隔左移，右肺受压不张。血常规：WBC 6.2×10⁹/L，Hb 118g/L。生化：总蛋白65g/L，白蛋白38g/L。凝血功能正常。");
        testCase.setCaseFh("父母均已故，死因不详。无家族遗传病史。");
        testCase.setCaseHid("否认肝炎、结核等传染病史。");
        testCase.setCaseDr("右侧胸腔积液，建议胸腔穿刺术明确病因并减轻症状。需排除恶性胸腔积液可能。");
        testCase.setContentWords("胸腔穿刺,胸腔积液,术前沟通,患者教育,知情同意");
        testCase.setTotleWords("重点关注患者心理状态，耐心解释胸腔穿刺的必要性、操作过程、可能的风险和并发症。使用通俗易懂的语言，消除患者恐惧心理，获得充分理解和配合。");
        testCase.setWriteWords("术前谈话记录应详细记录患者及家属对手术的理解程度、主要顾虑、医生的解释内容以及最终的知情同意结果。");
        testCase.setCaseMax(45L); // 胸腔穿刺术前沟通需要更长时间，45分钟

        // 先创建病例数据用于测试
        MvcResult caseResult = mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String caseResponse = caseResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("创建病例返回: {}", caseResponse);

        // 创建开始训练DTO
        startTrainingDTO = new StartTrainingDTO();
        startTrainingDTO.setCaseId(19L); // 假设创建的病例ID为1
        startTrainingDTO.setStepId(1); // 第1步
        startTrainingDTO.setEvalMode(1); // 训练模式

        // 创建测试会话对象
        testSession = new BizConsultationSessions();
        testSession.setUserId(1L);
        testSession.setCaseId(19L);
        testSession.setStepId(1);
        testSession.setCaseTitle("胸腔穿刺术前沟通病例");
        testSession.setPatientName("赵凤芹");
        testSession.setTotalDuration(0);
        testSession.setMessageCount(0);
        testSession.setStatus(0); // 进行中
        testSession.setEvalMode(1); // 训练模式
        testSession.setCreateTime(new Date());
    }

    /**
     * 测试查询学生问诊会话列表
     */
    @Test
    void testList() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/sessions/list")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.total").isNumber())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("会话列表返回: {}", response);
    }

    /**
     * 测试开始训练功能 - 正常情况
     */
    @Test
    void testStartTraining_Success() throws Exception {
        MvcResult result = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(startTrainingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("开始训练返回: {}", response);
        
        // 验证返回的sessionId不为空
        Integer sessionIdInt = JsonPath.read(response, "$.data");
        assertNotNull(sessionIdInt);
        Long sessionId = sessionIdInt.longValue();
        assertTrue(sessionId > 0);
    }

    /**
     * 测试开始训练功能 - 病例不存在的情况
     */
    @Test
    void testStartTraining_CaseNotFound() throws Exception {
        StartTrainingDTO invalidDTO = new StartTrainingDTO();
        invalidDTO.setCaseId(99999L); // 不存在的病例ID
        invalidDTO.setStepId(1);
        invalidDTO.setEvalMode(1);

        MvcResult result = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.msg").value("未找到对应的病例信息"))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("病例不存在时开始训练返回: {}", response);
    }

    /**
     * 测试开始训练功能 - 胸腔穿刺专项训练
     */
    @Test
    void testStartTraining_PleuracentesisSpecific() throws Exception {
        // 创建胸腔穿刺专项训练DTO
        StartTrainingDTO pleuracentesisDTO = new StartTrainingDTO();
        pleuracentesisDTO.setCaseId(1L);
        pleuracentesisDTO.setStepId(2); // 第2步：术前准备
        pleuracentesisDTO.setEvalMode(2); // 考试模式

        MvcResult result = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pleuracentesisDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("胸腔穿刺专项训练返回: {}", response);
        
        // 验证会话创建成功
        Integer sessionIdInt = JsonPath.read(response, "$.data");
        assertNotNull(sessionIdInt);
    }

    /**
     * 测试新增学生问诊会话
     */
    @Test
    void testAdd() throws Exception {
        MvcResult result = mockMvc.perform(post("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("新增会话返回: {}", response);
    }

    /**
     * 测试获取学生问诊会话详细信息
     */
    @Test
    void testGetInfo() throws Exception {
        // 先创建一个会话
        MvcResult addResult = mockMvc.perform(post("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isOk())
                .andReturn();
        
        String addResponse = addResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Integer sessionIdInt = JsonPath.read(addResponse, "$.data");
        Long sessionId = sessionIdInt.longValue();
        
        // 查询会话详情
        MvcResult result = mockMvc.perform(get("/system/sessions/" + sessionId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").exists())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("获取会话详情返回: {}", response);
    }

    /**
     * 测试修改学生问诊会话
     */
    @Test
    void testEdit() throws Exception {
        // 先创建一个会话
        MvcResult addResult = mockMvc.perform(post("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isOk())
                .andReturn();
        
        String addResponse = addResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Integer sessionIdInt = JsonPath.read(addResponse, "$.data");
        Long sessionId = sessionIdInt.longValue();
        
        // 修改会话信息
        testSession.setSessionId(sessionId);
        testSession.setStatus(1); // 修改为已完成
        testSession.setTotalDuration(1800); // 30分钟
        testSession.setMessageCount(10);
        
        MvcResult result = mockMvc.perform(put("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("修改会话返回: {}", response);
    }

    /**
     * 测试删除学生问诊会话
     */
    @Test
    void testRemove() throws Exception {
        // 先创建一个会话
        MvcResult addResult = mockMvc.perform(post("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testSession)))
                .andExpect(status().isOk())
                .andReturn();
        
        String addResponse = addResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Integer sessionIdInt = JsonPath.read(addResponse, "$.data");
        Long sessionId = sessionIdInt.longValue();
        
        // 删除会话
        MvcResult result = mockMvc.perform(delete("/system/sessions/" + sessionId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("删除会话返回: {}", response);
    }

    /**
     * 测试导出学生问诊会话列表
     */
    @Test
    void testExport() throws Exception {
        mockMvc.perform(post("/system/sessions/export")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
        
        log.info("导出会话列表测试完成");
    }

    /**
     * 测试结束训练功能
     */
    @Test
    void testFinishTraining() throws Exception {
//        // 先开始一个训练
//        MvcResult startResult = mockMvc.perform(post("/system/sessions/start")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(startTrainingDTO)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String startResponse = startResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
//        Integer sessionIdInt = JsonPath.read(startResponse, "$.data");
//        Long sessionId = sessionIdInt.longValue();
        
        // 结束训练
        MvcResult result = mockMvc.perform(post("/system/sessions/finish/" + "5")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("结束训练返回: {}", response);
    }

    /**
     * 测试胸腔穿刺训练的完整流程
     */
    @Test
    void testPleuracentesisTrainingFlow() throws Exception {
        log.info("=== 开始胸腔穿刺训练完整流程测试 ===");
        
        // 1. 开始训练
        MvcResult startResult = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(startTrainingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String startResponse = startResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        Integer sessionIdInt = JsonPath.read(startResponse, "$.data");
        Long sessionId = sessionIdInt.longValue();
        log.info("1. 开始训练成功，sessionId: {}", sessionId);
        
        // 2. 查询会话详情
        MvcResult detailResult = mockMvc.perform(get("/system/sessions/" + sessionId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.caseTitle").value("胸腔穿刺术前沟通病例"))
                .andExpect(jsonPath("$.data.patientName").value("赵凤芹"))
                .andReturn();
        
        String detailResponse = detailResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("2. 查询会话详情成功: {}", detailResponse);
        
        // 3. 模拟修改会话状态（进行中 -> 已完成）
        BizConsultationSessions updateSession = new BizConsultationSessions();
        updateSession.setSessionId(sessionId);
        updateSession.setStatus(1); // 已完成
        updateSession.setTotalDuration(2700); // 45分钟（胸腔穿刺术前沟通时间）
        updateSession.setMessageCount(15); // 对话轮次
        updateSession.setEndTime(new Date());
        
        MvcResult updateResult = mockMvc.perform(put("/system/sessions")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateSession)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String updateResponse = updateResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("3. 更新会话状态成功: {}", updateResponse);
        
        log.info("=== 胸腔穿刺训练完整流程测试完成 ===");
    }

    /**
     * 测试不同模式的训练启动
     */
    @Test
    void testStartTraining_DifferentModes() throws Exception {
        // 训练模式
        StartTrainingDTO trainingModeDTO = new StartTrainingDTO();
        trainingModeDTO.setCaseId(1L);
        trainingModeDTO.setStepId(1);
        trainingModeDTO.setEvalMode(1); // 训练模式

        MvcResult trainingResult = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingModeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String trainingResponse = trainingResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("训练模式开始成功: {}", trainingResponse);

        // 考试模式
        StartTrainingDTO examModeDTO = new StartTrainingDTO();
        examModeDTO.setCaseId(1L);
        examModeDTO.setStepId(1);
        examModeDTO.setEvalMode(2); // 考试模式

        MvcResult examResult = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(examModeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String examResponse = examResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("考试模式开始成功: {}", examResponse);
    }

    /**
     * 测试参数验证
     */
    @Test
    void testStartTraining_ParameterValidation() throws Exception {
        // 测试空DTO
        MvcResult emptyResult = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andReturn();
        
        String emptyResponse = emptyResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("空参数测试返回: {}", emptyResponse);

        // 测试缺少caseId的情况
        StartTrainingDTO incompleteDTO = new StartTrainingDTO();
        incompleteDTO.setStepId(1);
        incompleteDTO.setEvalMode(1);
        // 没有设置caseId

        MvcResult incompleteResult = mockMvc.perform(post("/system/sessions/start")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incompleteDTO)))
                .andExpect(status().isOk())
                .andReturn();
        
        String incompleteResponse = incompleteResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("缺少caseId参数测试返回: {}", incompleteResponse);
    }
}