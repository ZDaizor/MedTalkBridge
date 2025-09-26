package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.ActiveUsersWeeklyStats;
import com.ruoyi.bizcase.mapper.ActiveUsersWeeklyStatsMapper;
import com.ruoyi.bizcase.service.IActiveUsersWeeklyStatsService;

/**
 * 活跃用户周统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class ActiveUsersWeeklyStatsServiceImpl implements IActiveUsersWeeklyStatsService {
    
    @Autowired
    private ActiveUsersWeeklyStatsMapper activeUsersWeeklyStatsMapper;

    /**
     * 查询活跃用户周统计
     * 
     * @return 活跃用户周统计列表
     */
    @Override
    public List<ActiveUsersWeeklyStats> selectActiveUsersWeeklyStats() {
        return activeUsersWeeklyStatsMapper.selectActiveUsersWeeklyStats();
    }
}