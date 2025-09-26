package com.ruoyi.bizcase.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.bizcase.domain.BizCase;
import com.ruoyi.bizcase.service.IBizCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.ruoyi.system.service.ISysConfigService;

import java.util.Map;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * BizCase控制器集成测试类
 *
 * @author test
 * @date 2025-09-11
 */
@SpringBootTest(classes = RuoYiApplication.class)
@AutoConfigureWebMvc
@DisplayName("病例管理控制器集成测试")
public class BizCaseControllerIntegrationTest {

    Logger logger = Logger.getLogger(BizCaseControllerIntegrationTest.class.getName());

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private IBizCaseService bizCaseService;

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
        String password = "123456";
        java.util.HashMap<String, String> loginParams = new java.util.HashMap<>();
        loginParams.put("username", username);
        loginParams.put("password", password);
        String loginRequest = objectMapper.writeValueAsString(loginParams);

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
     * 创建有效的病例对象
     */
    private BizCase createValidBizCase() {
        BizCase bizCase = new BizCase();
        bizCase.setCaseName("测试病例");
        bizCase.setCaseCode("TEST001");
        bizCase.setCaseStatus(1L);
        bizCase.setCaseDifficulty(1);
        bizCase.setPatientName("张三");
        bizCase.setPatientAge(30L);
        bizCase.setPatientGender(1L);
        bizCase.setCaseSectionsType(1L);
        bizCase.setCaseAbstract("测试摘要");
        bizCase.setCaseHpi("现病史测试");
        bizCase.setCaseAic("主诉测试");
        return bizCase;
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试新增病例成功")
    void testAddBizCaseSuccess() throws Exception {
        // 准备测试数据
        BizCase bizCase = createValidBizCase();

        // Mock服务层返回成功
        when(bizCaseService.insertBizCase(any(BizCase.class))).thenReturn(1);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("操作成功"));
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试病例名称为空的验证")
    void testAddBizCaseWithEmptyName() throws Exception {
        // 准备测试数据 - 病例名称为空
        BizCase bizCase = createValidBizCase();
        bizCase.setCaseName("");

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试病例名称为null的验证")
    void testAddBizCaseWithNullName() throws Exception {
        // 准备测试数据 - 病例名称为null
        BizCase bizCase = createValidBizCase();
        bizCase.setCaseName(null);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试病例名称超长的验证")
    void testAddBizCaseWithTooLongName() throws Exception {
        // 准备测试数据 - 病例名称超长（超过100个字符）
        BizCase bizCase = createValidBizCase();
        StringBuilder longNameBuilder = new StringBuilder();
        for (int i = 0; i < 101; i++) {
            longNameBuilder.append("a");
        }
        String longName = longNameBuilder.toString(); // 101个字符
        bizCase.setCaseName(longName);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试病例编码为空的验证")
    void testAddBizCaseWithEmptyCode() throws Exception {
        // 准备测试数据 - 病例编码为空
        BizCase bizCase = createValidBizCase();
        bizCase.setCaseCode("");

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试患者姓名为空的验证")
    void testAddBizCaseWithEmptyPatientName() throws Exception {
        // 准备测试数据 - 患者姓名为空
        BizCase bizCase = createValidBizCase();
        bizCase.setPatientName("");

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试患者年龄为null的验证")
    void testAddBizCaseWithNullPatientAge() throws Exception {
        // 准备测试数据 - 患者年龄为null
        BizCase bizCase = createValidBizCase();
        bizCase.setPatientAge(null);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试患者年龄无效的验证")
    void testAddBizCaseWithInvalidPatientAge() throws Exception {
        // 准备测试数据 - 患者年龄无效（大于150）
        BizCase bizCase = createValidBizCase();
        bizCase.setPatientAge(151L);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = {"system:case:add"})
    @DisplayName("测试服务层异常情况")
    void testAddBizCaseServiceException() throws Exception {
        // 准备测试数据
        BizCase bizCase = createValidBizCase();

        // Mock服务层返回失败
        when(bizCaseService.insertBizCase(any(BizCase.class))).thenReturn(0);

        // 执行POST请求
        mockMvc.perform(post("/system/case")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bizCase)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.msg").value("操作失败"));
    }
}

