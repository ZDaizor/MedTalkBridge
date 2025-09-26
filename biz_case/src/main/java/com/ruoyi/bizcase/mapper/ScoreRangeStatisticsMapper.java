package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.ScoreRangeStatistics;

/**
 * 分数范围统计数据层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ScoreRangeStatisticsMapper {
    /**
     * 查询分数范围统计
     * 
     * @return 分数范围统计集合
     */
    List<ScoreRangeStatistics> selectScoreRangeStatistics();
}