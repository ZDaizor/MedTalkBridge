package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.ActiveUsersWeeklyStats;

/**
 * 活跃用户周统计Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IActiveUsersWeeklyStatsService {
    
    /**
     * 查询活跃用户周统计
     * 
     * @return 活跃用户周统计列表
     */
    List<ActiveUsersWeeklyStats> selectActiveUsersWeeklyStats();
}