package com.ruoyi.bizcase.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DashboardStatisticsMapper测试类
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@SpringBootTest
@ContextConfiguration(locations = {"classpath:application.yml"})
public class DashboardStatisticsMapperTest
{
    @Resource
    private DashboardStatisticsMapper dashboardStatisticsMapper;

    @Test
    public void testSelectDashboardStatistics()
    {
        Map<String, Object> result = dashboardStatisticsMapper.selectDashboardStatistics();
        assertNotNull(result);
        System.out.println("Dashboard Statistics Result: " + result);
    }
}