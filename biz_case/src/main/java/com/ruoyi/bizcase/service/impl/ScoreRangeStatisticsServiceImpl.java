package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.ScoreRangeStatistics;
import com.ruoyi.bizcase.mapper.ScoreRangeStatisticsMapper;
import com.ruoyi.bizcase.service.IScoreRangeStatisticsService;

/**
 * 分数范围统计服务层实现
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class ScoreRangeStatisticsServiceImpl implements IScoreRangeStatisticsService {
    
    @Autowired
    private ScoreRangeStatisticsMapper scoreRangeStatisticsMapper;

    /**
     * 查询分数范围统计
     * 
     * @return 分数范围统计集合
     */
    @Override
    public List<ScoreRangeStatistics> selectScoreRangeStatistics() {
        return scoreRangeStatisticsMapper.selectScoreRangeStatistics();
    }
}