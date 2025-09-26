package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.StageAssessmentStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * 阶段评估统计Mapper接口
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
@Mapper
public interface StageAssessmentStatisticsMapper {
    
    /**
     * 查询阶段评估统计
     * 
     * @return 阶段评估统计列表
     */
    List<StageAssessmentStatistics> selectStageAssessmentStatistics();
}