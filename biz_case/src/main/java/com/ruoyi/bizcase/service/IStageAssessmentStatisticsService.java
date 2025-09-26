package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.StageAssessmentStatistics;

/**
 * 阶段评估统计Service接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IStageAssessmentStatisticsService {
    
    /**
     * 查询阶段评估统计
     * 
     * @return 阶段评估统计列表
     */
    List<StageAssessmentStatistics> selectStageAssessmentStatistics();
}