package com.ruoyi.bizcase.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ruoyi.bizcase.domain.dto.StudentStatisticsQueryDTO;

/**
 * DashboardStatisticsMapper 测试类
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@SpringBootTest
public class DashboardStatisticsMapperParamTest {

    @Autowired
    private DashboardStatisticsMapper dashboardStatisticsMapper;
    
    @Test
    public void testSelectDashboardStatisticsByParams() {
        // 准备测试数据
        java.util.HashMap<String, Object> params = new java.util.HashMap<>();
        params.put("deptId", null); // 测试空参数
        params.put("studentName", null);
        
        // 执行查询
        Map<String, Object> result = dashboardStatisticsMapper.selectDashboardStatisticsByParams(params);
        
        // 验证结果
        assertNotNull(result);
        assertTrue(result.containsKey("totalStudents"));
        assertTrue(result.containsKey("averageScore"));
        assertTrue(result.containsKey("totalLearningDuration"));
        assertTrue(result.containsKey("totalExamCount"));
        
        System.out.println("Query result: " + result);
    }
}