package com.ruoyi.bizcase.service;

import com.ruoyi.bizcase.domain.DashboardStatistics;

/**
 * 仪表板统计应用服务接口
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
public interface IDashboardStatisticsService
{
    /**
     * 获取仪表板统计信息
     * 
     * @return 仪表板统计信息
     */
    DashboardStatistics getDashboardStatistics();
}