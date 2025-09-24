package com.ruoyi.bizcase.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.Map;

/**
 * 仪表板统计数据Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-24
 */
@Mapper
public interface DashboardStatisticsMapper
{
    /**
     * 查询仪表板统计信息
     * 
     * @return 统计信息映射
     */
    Map<String, Object> selectDashboardStatistics();
}