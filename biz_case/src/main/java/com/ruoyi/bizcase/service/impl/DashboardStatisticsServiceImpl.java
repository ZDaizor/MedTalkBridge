package com.ruoyi.bizcase.service.impl;

import com.ruoyi.bizcase.domain.DashboardStatistics;
import com.ruoyi.bizcase.repository.DashboardStatisticsRepository;
import com.ruoyi.bizcase.service.IDashboardStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 仪表板统计应用服务实现类
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Service
public class DashboardStatisticsServiceImpl implements IDashboardStatisticsService
{
    @Autowired
    private DashboardStatisticsRepository dashboardStatisticsRepository;

    @Override
    public DashboardStatistics getDashboardStatistics()
    {
        return dashboardStatisticsRepository.getDashboardStatistics();
    }
}