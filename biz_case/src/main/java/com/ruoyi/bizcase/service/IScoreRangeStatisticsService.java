package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.ScoreRangeStatistics;

/**
 * 分数范围统计服务层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IScoreRangeStatisticsService {
    /**
     * 查询分数范围统计
     * 
     * @return 分数范围统计集合
     */
    List<ScoreRangeStatistics> selectScoreRangeStatistics();
}