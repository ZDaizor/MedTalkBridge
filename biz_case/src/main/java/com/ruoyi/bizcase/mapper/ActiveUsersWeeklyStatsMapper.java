package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.ActiveUsersWeeklyStats;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活跃用户周统计Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface ActiveUsersWeeklyStatsMapper {
    
    /**
     * 查询活跃用户周统计
     * 
     * @return 活跃用户周统计列表
     */
    List<ActiveUsersWeeklyStats> selectActiveUsersWeeklyStats();
}