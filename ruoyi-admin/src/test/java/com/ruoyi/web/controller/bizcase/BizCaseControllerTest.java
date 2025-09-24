package com.ruoyi.web.controller.bizcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.bizcase.domain.BizCase;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BizCaseControllerTest {

    private static final Logger log = LoggerFactory.getLogger(BizCaseControllerTest.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BizCase testCase;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
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
    void testList() throws Exception {
        MvcResult authorization = mockMvc.perform(get("/system/case/list")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.total").isNumber()).andReturn();
        String response = authorization.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("列表返回: {}", response);
    }

    @Test
    void testGetInfo() throws Exception {
        MvcResult result = mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        String addResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("新增返回: {}", addResponse);
        // 假设新增后ID为1
        MvcResult infoResult = mockMvc.perform(get("/system/case/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200)).andReturn();
        String infoResponse = infoResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("详情返回: {}", infoResponse);
    }

    @Test
    void testAdd() throws Exception {
        mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testEdit() throws Exception {
        MvcResult addResult = mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase)))
                .andReturn();
        BizCase createdCase = new BizCase();
        createdCase.setCaseId(1L); // Assume ID
        createdCase.setCaseName("修改后的测试病例");
        mockMvc.perform(put("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createdCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testRemove() throws Exception {
        mockMvc.perform(delete("/system/case/3")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }

    @Test
    void testExport() throws Exception {
        mockMvc.perform(post("/system/case/export")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void testQueryByNameDifficultyStatus() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/case/list")
                        .header("Authorization", "Bearer " + token)
                        .param("caseName", "测试")
                        .param("caseDifficulty", "1")
                        .param("caseStatus", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.rows").isArray())
                .andReturn();
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("条件查询返回: {}", response);
        // 可进一步断言每条结果都符合条件
    }

    @Test
    void testAutoGenerateCaseCode() throws Exception {
        // 创建另一个胸腔穿刺相关病例用于测试自动编码功能
        BizCase noCodeCase = new BizCase();
        noCodeCase.setCaseName("胸腔穿刺术后护理沟通病例");
        noCodeCase.setCaseStatus(1L);
        noCodeCase.setPatientName("赵凤芹");
        noCodeCase.setPatientAge(69L);
        noCodeCase.setPatientGender(2L);
        noCodeCase.setCaseDifficulty(2); // 中级
        noCodeCase.setCaseSectionsType(203L); // 呼吸系统疾病
        noCodeCase.setCaseAbstract("胸腔穿刺术后第一天，患者询问关于术后注意事项、何时可以活动、伤口护理等问题。");
        noCodeCase.setCaseHpi("胸腔穿刺术后12小时，穿刺点无渗血，胸闷症状明显缓解，患者精神状态良好。");
        noCodeCase.setCaseHa("无药物过敏史。");
        noCodeCase.setCasePh("胸腔穿刺术顺利完成，抽出淡黄色胸水800ml，术中患者配合良好。");
        noCodeCase.setCaseAic("术后咨询");
        noCodeCase.setCaseHe("一般情况良好，穿刺点敷料干燥，无渗血。呼吸平稳，右肺呼吸音较前增强。");
        noCodeCase.setCaseAe("术后胸片示右肺复张良好，少量残余胸水。");
        noCodeCase.setCaseFh("无相关家族史。");
        noCodeCase.setCaseHid("无传染病史。");
        noCodeCase.setCaseDr("胸腔穿刺术后，注意观察生命体征，预防并发症发生。");
        noCodeCase.setContentWords("术后护理,患者教育,康复指导,并发症预防");
        noCodeCase.setTotleWords("术后护理沟通要点：解释术后注意事项，指导活动范围，强调复查的重要性。");
        noCodeCase.setWriteWords("护理记录应详细记录患者术后状态、健康教育内容及患者理解情况。");
        noCodeCase.setCaseMax(25L);

        MvcResult result = mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noCodeCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        String addResponse = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("自动编码新增返回: {}", addResponse);
    }

    /**
     * 测试胸腔穿刺病例的难度级别查询
     */
    @Test
    void testQueryHighDifficultyCases() throws Exception {
        // 先添加胸腔穿刺病例
        mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase)))
                .andExpect(status().isOk());

        // 查询高级难度病例
        MvcResult result = mockMvc.perform(get("/system/case/list")
                        .header("Authorization", "Bearer " + token)
                        .param("caseDifficulty", "3")) // 高级难度
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.rows").isArray())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("高级难度病例查询返回: {}", response);
    }

    /**
     * 测试按症状分类查询胸腔穿刺相关病例
     */
    @Test
    void testQueryBySectionsType() throws Exception {
        MvcResult result = mockMvc.perform(get("/system/case/list")
                        .header("Authorization", "Bearer " + token)
                        .param("caseSectionsType", "203")) // 呼吸系统疾病
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.rows").isArray())
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("呼吸系统疾病病例查询返回: {}", response);
    }

    /**
     * 测试胸腔穿刺病例的患者年龄范围验证
     */
    @Test
    void testElderlyPatientCase() throws Exception {
        // 创建老年患者病例
        BizCase elderlyCase = new BizCase();
        elderlyCase.setCaseName("老年胸腔积液病例");
        elderlyCase.setCaseStatus(1L);
        elderlyCase.setPatientName("王老太");
        elderlyCase.setPatientAge(85L); // 85岁高龄患者
        elderlyCase.setPatientGender(2L);
        elderlyCase.setCaseDifficulty(3);
        elderlyCase.setCaseSectionsType(203L);
        elderlyCase.setCaseAbstract("85岁高龄患者，因呼吸困难需行胸腔穿刺，但需考虑高龄手术风险。");
        elderlyCase.setCaseHpi("呼吸困难1周，逐渐加重。");
        elderlyCase.setCaseHa("无药物过敏史。");
        elderlyCase.setCasePh("高血压、糖尿病10年。");
        elderlyCase.setCaseAic("呼吸困难");
        elderlyCase.setCaseHe("一般情况差，血压150/90mmHg，心率不齐。");
        elderlyCase.setCaseAe("胸部CT示双侧胸腔积液。");
        elderlyCase.setCaseFh("无特殊家族史。");
        elderlyCase.setCaseHid("无传染病史。");
        elderlyCase.setCaseDr("老年胸腔积液，需评估手术风险效益比。");
        elderlyCase.setContentWords("老年患者,高龄手术,风险评估");
        elderlyCase.setTotleWords("重点关注老年患者的手术风险，需与家属充分沟通。");
        elderlyCase.setWriteWords("详细记录风险评估过程和家属沟通内容。");
        elderlyCase.setCaseMax(60L); // 老年患者沟通时间更长

        MvcResult result = mockMvc.perform(post("/system/case")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elderlyCase)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("老年患者病例新增返回: {}", response);
    }

    /**
     * 测试胸腔穿刺病例的批量操作
     */
    @Test
    @Transactional
    void testBatchCaseOperations() throws Exception {
        // 批量创建胸腔穿刺相关病例
        List<BizCase> cases = createPleuracentesisCases();
        
        for (BizCase caseItem : cases) {
            mockMvc.perform(post("/system/case")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(caseItem)))
                    .andExpect(status().isOk());
        }
        
        // 验证批量查询
        MvcResult result = mockMvc.perform(get("/system/case/list")
                        .header("Authorization", "Bearer " + token)
                        .param("caseName", "胸腔")) // 按名称模糊查询
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andReturn();
        
        String response = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        log.info("批量病例查询返回: {}", response);
    }

    /**
     * 辅助方法：创建多个胸腔穿刺相关病例
     */
    private List<BizCase> createPleuracentesisCases() {
        List<BizCase> cases = new ArrayList<>();
        
        // 胸腔穿刺术前病例
        BizCase preOpCase = new BizCase();
        preOpCase.setCaseName("胸腔穿刺术前准备病例");
        preOpCase.setPatientName("赵凤芹");
        preOpCase.setPatientAge(69L);
        preOpCase.setPatientGender(2L);
        preOpCase.setCaseDifficulty(3);
        preOpCase.setCaseStatus(1L);
        preOpCase.setCaseSectionsType(203L);
        preOpCase.setCaseAbstract("术前访视，解释手术必要性，消除患者恐惧心理。");
        cases.add(preOpCase);
        
        // 胸腔穿刺术中沟通病例
        BizCase intraOpCase = new BizCase();
        intraOpCase.setCaseName("胸腔穿刺术中沟通病例");
        intraOpCase.setPatientName("赵凤芹");
        intraOpCase.setPatientAge(69L);
        intraOpCase.setPatientGender(2L);
        intraOpCase.setCaseDifficulty(3);
        intraOpCase.setCaseStatus(1L);
        intraOpCase.setCaseSectionsType(203L);
        intraOpCase.setCaseAbstract("术中与患者沟通，指导体位配合，监测患者反应。");
        cases.add(intraOpCase);
        
        // 胸腔穿刺并发症处理病例
        BizCase complicationCase = new BizCase();
        complicationCase.setCaseName("胸腔穿刺并发症处理病例");
        complicationCase.setPatientName("赵凤芹");
        complicationCase.setPatientAge(69L);
        complicationCase.setPatientGender(2L);
        complicationCase.setCaseDifficulty(3);
        complicationCase.setCaseStatus(1L);
        complicationCase.setCaseSectionsType(203L);
        complicationCase.setCaseAbstract("术中出现气胸并发症，需要紧急处理并与患者家属沟通。");
        cases.add(complicationCase);
        
        return cases;
    }
}
