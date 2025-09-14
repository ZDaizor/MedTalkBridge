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
        testCase = new BizCase();
        testCase.setCaseName("高血压随访病例");
        testCase.setCaseStatus(1L);
        testCase.setPatientName("张三");
        testCase.setPatientAge(58L);
        testCase.setPatientGender(1L); // 男
        testCase.setCaseDifficulty(2); // 中级
        testCase.setCaseSectionsType(101L); // 假设101为高血压分类
        testCase.setCaseAbstract("患者因头晕来诊，既往高血压病史10年。");
        testCase.setCaseHpi("头晕2天，无胸闷、气促。");
        testCase.setCaseHa("无药物过敏史。");
        testCase.setCasePh("高血压10年，规律服药。");
        testCase.setCaseAic("头晕");
        testCase.setCaseHe("血压160/100mmHg，心率80次/分。");
        testCase.setCaseAe("心电图正常，肾功能正常。");
        testCase.setCaseFh("父亲有高血压史。");
        testCase.setCaseHid("无传染病史。");
        testCase.setCaseDr("高血压急症排除，建议调整降压方案。");
        testCase.setContentWords("高血压,头晕,随访");
        testCase.setTotleWords("注意血压监测，药物依从性。");
        testCase.setWriteWords("病志完整，逻辑清晰。");
        testCase.setCaseMax(30L); // 问诊最大时间30分钟

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
        BizCase noCodeCase = new BizCase();
        noCodeCase.setCaseName("自动编码测试病例");
        noCodeCase.setCaseStatus(1L);
        noCodeCase.setPatientName("李四");
        noCodeCase.setPatientAge(45L);
        noCodeCase.setPatientGender(1L);
        noCodeCase.setCaseDifficulty(1);
        noCodeCase.setCaseSectionsType(102L);
        noCodeCase.setCaseAbstract("患者因头痛来诊，既往高血压病史5年。");
        noCodeCase.setCaseHpi("头痛1天，无其他不适。");
        noCodeCase.setCaseHa("无药物过敏史。");
        noCodeCase.setCasePh("高血压5年，偶尔服药。");
        noCodeCase.setCaseAic("头痛");
        noCodeCase.setCaseHe("血压150/95mmHg，心率75次/分。");
        noCodeCase.setCaseAe("心电图正常。");
        noCodeCase.setCaseFh("母亲有高血压史。");
        noCodeCase.setCaseHid("无传染病史。");
        noCodeCase.setCaseDr("建议规律服药，复查血压。");
        noCodeCase.setContentWords("高血压,头痛,随访");
        noCodeCase.setTotleWords("注意血压监测。");
        noCodeCase.setWriteWords("病志完整。");
        noCodeCase.setCaseMax(20L);

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
}
