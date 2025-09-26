package com.ruoyi.bizcase.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bizcase.domain.StageAssessmentStatistics;
import com.ruoyi.bizcase.mapper.StageAssessmentStatisticsMapper;
import com.ruoyi.bizcase.service.IStageAssessmentStatisticsService;

/**
 * 阶段评估统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Service
public class StageAssessmentStatisticsServiceImpl implements IStageAssessmentStatisticsService {
    
    @Autowired
    private StageAssessmentStatisticsMapper stageAssessmentStatisticsMapper;

    /**
     * 查询阶段评估统计
     * 
     * @return 阶段评估统计列表
     */
    @Override
    public List<StageAssessmentStatistics> selectStageAssessmentStatistics() {
        return stageAssessmentStatisticsMapper.selectStageAssessmentStatistics();
    }
}