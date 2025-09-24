package com.ruoyi.bizcase.service;

import com.ruoyi.bizcase.domain.DashboardStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DashboardStatisticsService测试类
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@SpringBootTest
public class DashboardStatisticsServiceTest
{
    @Resource
    private IDashboardStatisticsService dashboardStatisticsService;

    @Test
    public void testGetDashboardStatistics()
    {
        DashboardStatistics result = dashboardStatisticsService.getDashboardStatistics();
        assertNotNull(result);
        System.out.println("Dashboard Statistics: " + result);
    }
}