package com.ruoyi.bizcase.mapper;

import java.util.List;
import com.ruoyi.bizcase.domain.ExamStatistics;

/**
 * 考试统计数据层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface ExamStatisticsMapper {
    /**
     * 查询考试统计
     * 
     * @return 考试统计集合
     */
    List<ExamStatistics> selectExamStatistics();
}