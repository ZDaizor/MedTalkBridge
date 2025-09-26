package com.ruoyi.bizcase.repository;

import com.ruoyi.bizcase.domain.DashboardStatistics;
import com.ruoyi.bizcase.domain.dto.StudentStatisticsQueryDTO;
import java.util.Map;

/**
 * 仪表板统计数据访问接口
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
public interface DashboardStatisticsRepository
{
    /**
     * 获取仪表板统计信息
     * 
     * @return 仪表板统计信息
     */
    DashboardStatistics getDashboardStatistics();
    
    /** 
     * 获取仪表板统计信息对应的原始数据
     * 
     * @return 统计数据映射
     */
    Map<String, Object> getRawDashboardStatistics();
    
    /**
     * 根据参数获取仪表板统计信息
     * 
     * @param queryDTO 查询参数
     * @return 仪表板统计信息
     */
    DashboardStatistics getDashboardStatisticsByParams(StudentStatisticsQueryDTO queryDTO);
}