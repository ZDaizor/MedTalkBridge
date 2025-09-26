package com.ruoyi.bizcase.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.bizcase.domain.ActiveUsersWeeklyStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 仪表板统计信息控制器周活跃用户分析接口测试类
 *
 * @author ruoyi
 * @date 2025-09-26
 */
@SpringBootTest(classes = RuoYiApplication.class)
@AutoConfigureWebMvc
@DisplayName("仪表板统计信息周活跃用户分析接口测试")
public class DashboardStatisticsControllerWeeklyActiveTest {

    Logger logger = Logger.getLogger(DashboardStatisticsControllerWeeklyActiveTest.class.getName());

    @Autowired
    private WebApplicationContext webApplicationContext;

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

    @Test
    @WithMockUser(authorities = {"system:dashboard:statistics"})
    @DisplayName("测试获取周活跃用户统计成功")
    void testGetWeeklyActiveUsersSuccess() throws Exception {
        logger.info("开始测试获取周活跃用户统计功能");

        // 执行GET请求
        MvcResult result = mockMvc.perform(get("/system/dashboard/weekly-active-users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("操作成功"))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseContent);
        
        // 验证返回成功
        assertEquals(200, jsonNode.get("code").asInt(), "返回码应该为200");
        assertTrue(jsonNode.has("data"), "响应应该包含data字段");

        // 验证数据格式
        JsonNode dataNode = jsonNode.get("data");
        assertTrue(dataNode.isArray(), "data应该是一个数组");
        
        // 验证数组长度（应该是7天的数据）
        List<ActiveUsersWeeklyStats> statsList = objectMapper.readValue(
                dataNode.toString(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, ActiveUsersWeeklyStats.class));
        
        assertEquals(7, statsList.size(), "应该返回7天的数据");

        // 验证每天的数据结构
        for (int i = 0; i < statsList.size(); i++) {
            ActiveUsersWeeklyStats stat = statsList.get(i);
            assertNotNull(stat.getDayOfWeek(), "星期字段不应为空");
            assertNotNull(stat.getDayNumber(), "星期编号字段不应为空");
            assertNotNull(stat.getActiveUsers(), "活跃用户数字段不应为空");
            
            // 验证星期编号在1-7范围内
            assertTrue(stat.getDayNumber() >= 1 && stat.getDayNumber() <= 7, 
                    "星期编号应该在1-7之间，实际值：" + stat.getDayNumber());
            
            // 验证星期名称
            String dayOfWeek = stat.getDayOfWeek();
            assertTrue(dayOfWeek.equals("周日") || dayOfWeek.equals("周一") || 
                      dayOfWeek.equals("周二") || dayOfWeek.equals("周三") || 
                      dayOfWeek.equals("周四") || dayOfWeek.equals("周五") || 
                      dayOfWeek.equals("周六"), 
                      "星期名称应该为中文表示，实际值：" + dayOfWeek);
            
            // 验证活跃用户数不为负数
            assertTrue(stat.getActiveUsers() >= 0, 
                    "活跃用户数不应该为负数，实际值：" + stat.getActiveUsers());
        }
        
        logger.info("测试通过：获取周活跃用户统计功能正常，返回" + statsList.size() + "天数据");
    }

    @Test
    @WithMockUser(authorities = {"system:dashboard:statistics"})
    @DisplayName("测试获取周活跃用户统计的数据完整性")
    void testGetWeeklyActiveUsersDataCompleteness() throws Exception {
        logger.info("开始测试周活跃用户统计数据完整性");

        // 执行GET请求
        MvcResult result = mockMvc.perform(get("/system/dashboard/weekly-active-users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(responseContent);
        JsonNode dataNode = jsonNode.get("data");
        
        // 验证数据完整性
        List<ActiveUsersWeeklyStats> statsList = objectMapper.readValue(
                dataNode.toString(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, ActiveUsersWeeklyStats.class));
        
        // 验证7天数据都存在（即使活跃用户数为0）
        assertEquals(7, statsList.size(), "应该返回7天的完整数据");
        
        // 验证星期编号连续性
        for (int i = 1; i <= 7; i++) {
            boolean found = false;
            for (ActiveUsersWeeklyStats stat : statsList) {
                if (stat.getDayNumber().equals(i)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "应该包含星期编号为 " + i + " 的数据");
        }
        
        logger.info("测试通过：周活跃用户统计数据完整性验证正常");
    }

    @Test
    @WithMockUser(authorities = {"system:dashboard:statistics"})
    @DisplayName("测试非管理员用户访问周活跃用户统计")
    void testGetWeeklyActiveUsersWithoutPermission() throws Exception {
        logger.info("开始测试无权限用户访问周活跃用户统计数据");

        // 执行GET请求但不设置权限（或者使用没有权限的用户）
        mockMvc.perform(get("/system/dashboard/weekly-active-users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
                
        logger.info("测试通过：无权限用户访问被正确拒绝");
    }

    @Test
    @DisplayName("测试周活跃用户统计数据结构")
    void testWeeklyActiveUsersDataStructure() {
        logger.info("开始测试周活跃用户统计数据结构");

        // 创建测试数据
        ActiveUsersWeeklyStats stat = new ActiveUsersWeeklyStats();
        stat.setDayOfWeek("周一");
        stat.setDayNumber(2);
        stat.setActiveUsers(10L);

        // 验证数据结构
        assertEquals("周一", stat.getDayOfWeek(), "星期应该正确设置");
        assertEquals(Integer.valueOf(2), stat.getDayNumber(), "星期编号应该正确设置");
        assertEquals(Long.valueOf(10), stat.getActiveUsers(), "活跃用户数应该正确设置");

        // 验证设为0的情况
        stat.setActiveUsers(0L);
        assertEquals(Long.valueOf(0), stat.getActiveUsers(), "活跃用户数可以设置为0");

        logger.info("测试通过：周活跃用户统计数据结构正常");
    }
}