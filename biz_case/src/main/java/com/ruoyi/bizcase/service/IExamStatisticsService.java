package com.ruoyi.bizcase.service;

import java.util.List;
import com.ruoyi.bizcase.domain.ExamStatistics;

/**
 * 考试统计服务层
 * 
 * @author ruoyi
 * @date 2025-09-26
 */
public interface IExamStatisticsService {
    /**
     * 查询考试统计
     * 
     * @return 考试统计集合
     */
    List<ExamStatistics> selectExamStatistics();
}